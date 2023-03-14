package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.Entity;
import lombok.Data;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.PointsRewardRepository;

import java.sql.Date;


@Entity
@Data
public class PointsReward extends Reward {


    public PointsReward(String tenant, Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double balance, String remarks, Long previousPointsTransaction) {
        super(tenant, id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, balance, remarks, previousPointsTransaction );
    }

    public PointsReward(AddRewardDto addRewardDto, CardRepository cardRepository, PointsRewardRepository pointsRewardRepository) {

        this(addRewardDto.getTenant(), null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), 0.0, addRewardDto.getRemarks(), null);

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();
        PointsReward previousPointsReward = pointsRewardRepository.findTopByCardOrderByIdDesc(card).get();

        updateBalance(previousPointsReward.getBalance());

        this.setCard(card);
        this.setPreviousTransaction(previousPointsReward.getId());
    }

    public PointsReward() {
    }

    private void updateBalance(Double previousBalance) {
        this.setBalance(previousBalance + this.getRewardAmount());
    }

}
