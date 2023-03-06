package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.Entity;
import lombok.Data;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.MilesRewardRepository;

import java.sql.Date;


@Entity
@Data
public class MilesReward extends Reward {


    public MilesReward(String tenant, Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double rewardBonusAmount, Double balance, String remarks) {
        super(tenant, id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, rewardBonusAmount, balance, remarks );
    }

    public MilesReward(AddRewardDto addRewardDto, CardRepository cardRepository) {
        this(addRewardDto.getTenant(), null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), addRewardDto.getRewardBonusAmount(), 0.0, addRewardDto.getRemarks());

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();

        this.setCard(card);
    }

    public MilesReward() {
    }

    public void updateBalance(Double rewardBasedAmount, MilesRewardRepository milesRewardRepository) {
        MilesReward previousMilesReward = milesRewardRepository.findTopByOrderByIdDesc().orElse(new MilesReward());

        this.setBalance(previousMilesReward.getBalance() + rewardBasedAmount);
    }


}
