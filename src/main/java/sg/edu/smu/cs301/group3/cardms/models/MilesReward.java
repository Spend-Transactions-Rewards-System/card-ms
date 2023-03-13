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


    public MilesReward(String tenant, Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double balance, String remarks, MilesReward previousMilesReward) {
        super(tenant, id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, balance, remarks, previousMilesReward );
    }

    public MilesReward(AddRewardDto addRewardDto, CardRepository cardRepository, MilesRewardRepository milesRewardRepository) {
        this(addRewardDto.getTenant(), null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), 0.0, addRewardDto.getRemarks(), null);

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();
        MilesReward previousMilesReward = milesRewardRepository.findTopByCardOrderByIdDesc(card).get();

        updateBalance(previousMilesReward.getBalance());

        this.setCard(card);
        this.setPreviousTransaction(previousMilesReward);
    }

    public MilesReward() {
    }

    private void updateBalance(Double previousBalance) {
        this.setBalance(previousBalance + this.getRewardAmount());
    }


}
