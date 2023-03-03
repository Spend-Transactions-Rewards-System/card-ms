package sg.edu.smu.cs301.group3.cardms.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.models.*;
import sg.edu.smu.cs301.group3.cardms.repositories.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RewardServiceImpl implements RewardService{

    private final CardRepository cardRepository;
    private final MilesRewardRepository milesRewardRepository;

    private final PointsRewardRepository pointsRewardRepository;

    private final CashbackRewardRepository cashbackRewardRepository;

    private final CustomerRepository customerRepository;
    @Override
    public RewardDto addEarnedReward(AddRewardDto addRewardDto) {

        //todo: implement the business logic if given cardId is not found
        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).orElseThrow(() -> new EntityNotFoundException(""));

        Reward savedReward = null;

        if(card.getRewardType().equals(RewardType.miles)) {
            MilesReward milesReward = new MilesReward(addRewardDto, cardRepository);
            savedReward = milesRewardRepository.save(milesReward);
        }

        if(card.getRewardType().equals(RewardType.points)) {
            PointsReward pointsReward = new PointsReward(addRewardDto, cardRepository);
            savedReward = pointsRewardRepository.save(pointsReward);
        }

        if(card.getRewardType().equals(RewardType.cashback)) {
            CashbackReward cashbackReward = new CashbackReward(addRewardDto, cardRepository);
            savedReward = cashbackRewardRepository.save(cashbackReward);
        }

        return new RewardDto(savedReward);
    }

    @Override
    public List<RewardDto> getCustomerEarnedRewards(String customerId) {

        //todo: implement customer not found handler
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException());

        List<Reward> rewards = new ArrayList<>();

        customer.getCards().stream().forEach(card -> {
            if(card.getRewardType().equals(RewardType.miles))
                rewards.addAll(milesRewardRepository.findAllByCard(card));

            if(card.getRewardType().equals(RewardType.points))
                rewards.addAll(pointsRewardRepository.findAllByCard(card));

            if(card.getRewardType().equals(RewardType.cashback))
                rewards.addAll(cashbackRewardRepository.findAllByCard(card));
        });

        List<RewardDto> result = new ArrayList<>();

        rewards.stream().forEach(reward -> {
            result.add(new RewardDto(reward));
        });

        return result;
    }

    @Override
    public List<RewardDto> getCardEarnedRewards(String customerId, String cardType) {

        //todo: implement customer not found handler
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new EntityNotFoundException());

        Set<Card> cardSet = customer.getCards().stream().filter(card -> card.getCardType().equalsIgnoreCase(cardType)).collect(Collectors.toSet());

        List<Reward> rewards = new ArrayList<>();

        //todo: implement if no card is found handler
        Card card = cardSet.iterator().next();

        if(card.getRewardType().equals(RewardType.miles))
            rewards.addAll(milesRewardRepository.findAllByCard(card));

        if(card.getRewardType().equals(RewardType.points))
            rewards.addAll(pointsRewardRepository.findAllByCard(card));

        if(card.getRewardType().equals(RewardType.cashback))
            rewards.addAll(cashbackRewardRepository.findAllByCard(card));

        List<RewardDto> result = new ArrayList<>();

        rewards.stream().forEach(reward -> {
            result.add(new RewardDto(reward));
        });

        return result;
    }

}