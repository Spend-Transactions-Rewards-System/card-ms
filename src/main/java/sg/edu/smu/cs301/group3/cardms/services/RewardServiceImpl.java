package sg.edu.smu.cs301.group3.cardms.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.models.*;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CashbackRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.MilesRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.PointsRewardRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RewardServiceImpl implements RewardService{

    private final CardRepository cardRepository;
    private final MilesRewardRepository milesRewardRepository;

    private final PointsRewardRepository pointsRewardRepository;

    private final CashbackRewardRepository cashbackRewardRepository;
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
    public List<RewardDto> getEarnedRewards(String customerId) {
        return null;
    }

    @Override
    public RewardDto getEarnedReward(String customerId, String cardType) {
        return null;
    }

}
