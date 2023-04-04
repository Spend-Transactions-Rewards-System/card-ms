package sg.edu.smu.cs301.group3.cardms.services;

import io.awspring.cloud.sqs.operations.SqsOperations;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.CampaignBonusAlertDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.*;
import sg.edu.smu.cs301.group3.cardms.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RewardServiceImpl implements RewardService {

    private final CardRepository cardRepository;
    private final MilesRewardRepository milesRewardRepository;
    private final PointsRewardRepository pointsRewardRepository;
    private final CashbackRewardRepository cashbackRewardRepository;
    private final CustomerRepository customerRepository;
    private final SqsOperations sqsOperations;
    Logger logger = LoggerFactory.getLogger(RewardServiceImpl.class);
    @Value("${aws.card.to.campaign.queue.url}")
    private String cardToCampaignQueueUrl;

    @Transactional
    @Override
    public RewardDto addEarnedReward(AddRewardDto addRewardDto) {

        if (isDuplicatedReward(addRewardDto, milesRewardRepository)) {
            return null;
        }

        if (isDuplicatedReward(addRewardDto, pointsRewardRepository)) {
            return null;
        }

        if (isDuplicatedReward(addRewardDto, cashbackRewardRepository)) {
            return null;
        }

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).orElseThrow(() -> new EntityNotFoundException(""));

        Reward savedReward = null;

        if (card.getRewardType().equals(RewardType.miles)) {
            MilesReward milesReward = new MilesReward(addRewardDto, cardRepository, milesRewardRepository);
            savedReward = milesRewardRepository.save(milesReward);
        }

        if (card.getRewardType().equals(RewardType.points)) {
            PointsReward pointsReward = new PointsReward(addRewardDto, cardRepository, pointsRewardRepository);
            savedReward = pointsRewardRepository.save(pointsReward);
        }

        if (card.getRewardType().equals(RewardType.cashback)) {
            CashbackReward cashbackReward = new CashbackReward(addRewardDto, cardRepository, cashbackRewardRepository);
            savedReward = cashbackRewardRepository.save(cashbackReward);
        }

        if (savedReward.getRemarks().contains("Campaign reward")) {
            CampaignBonusAlertDto campaignBonusAlertDto = CampaignBonusAlertDto.builder()
                    .email(savedReward.getCard().getCustomer().getEmail())
                    .spendDate(DateHelper.dateFormat().format(savedReward.getTransactionDate()))
                    .merchant(savedReward.getMerchant())
                    .cardType(savedReward.getCard().getCardType())
                    .currency(savedReward.getCurrency().name())
                    .amount(savedReward.getAmount())
                    .rewardAmount(savedReward.getRewardAmount())
                    .balance(savedReward.getBalance())
                    .remarks(savedReward.getRemarks())
                    .build();

            logger.info("SENDING: " + campaignBonusAlertDto);

            sqsOperations.send(cardToCampaignQueueUrl, MessageBuilder.withPayload(campaignBonusAlertDto).build());
        }

        return new RewardDto(savedReward);
    }

    private boolean isDuplicatedReward(AddRewardDto addRewardDto, RewardRepository rewardRepository) {


        if (!(rewardRepository.findByTenantAndTransactionIdAndRemarks(addRewardDto.getTenant(), addRewardDto.getTransactionId(), addRewardDto.getRemarks()).isEmpty())) {
            logger.error("duplicated reward: " + addRewardDto);
            return true;
        }

        return false;
    }

    @Override
    public List<RewardDto> getCustomerEarnedRewards(String tenant, String customerId) {

        Optional<Customer> customerObj = customerRepository.findById(customerId);

        if (customerObj.isPresent()) {
            List<Reward> rewards = new ArrayList<>();

            Customer customer = customerObj.get();

            customer.getCards().stream().forEach(card -> {
                if (card.getRewardType().equals(RewardType.miles))
                    rewards.addAll(milesRewardRepository.findAllByCard(card));

                if (card.getRewardType().equals(RewardType.points))
                    rewards.addAll(pointsRewardRepository.findAllByCard(card));

                if (card.getRewardType().equals(RewardType.cashback))
                    rewards.addAll(cashbackRewardRepository.findAllByCard(card));
            });

            List<RewardDto> result = new ArrayList<>();

            rewards.stream().forEach(reward -> {
                result.add(new RewardDto(reward));
            });

            return result;
        } else {
            throw new EntityNotFoundException("Customer not found");
        }
    }

    @Override
    public List<RewardDto> getCardEarnedRewards(String tenant, String customerId, String cardType) {

        //todo: implement customer not found handler
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException());

        Set<Card> cardSet = customer.getCards().stream().filter(card -> card.getCardType().equalsIgnoreCase(cardType)).collect(Collectors.toSet());

        List<Reward> rewards = new ArrayList<>();

        //todo: implement if no card is found handler
        Card card = cardSet.iterator().next();

        if (card.getRewardType().equals(RewardType.miles))
            rewards.addAll(milesRewardRepository.findAllByCard(card));

        if (card.getRewardType().equals(RewardType.points))
            rewards.addAll(pointsRewardRepository.findAllByCard(card));

        if (card.getRewardType().equals(RewardType.cashback))
            rewards.addAll(cashbackRewardRepository.findAllByCard(card));

        List<RewardDto> result = new ArrayList<>();

        rewards.stream().forEach(reward -> {
            result.add(new RewardDto(reward));
        });

        return result;
    }

}
