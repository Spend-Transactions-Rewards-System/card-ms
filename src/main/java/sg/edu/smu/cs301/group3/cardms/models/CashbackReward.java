package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.Entity;
import lombok.Data;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CashbackRewardRepository;

import java.sql.Date;


@Entity
@Data
public class CashbackReward extends Reward {


    public CashbackReward(Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double rewardBonusAmount, Double balance, String remarks) {
        super(id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, rewardBonusAmount, balance, remarks );
    }

    public CashbackReward(AddRewardDto addRewardDto, CardRepository cardRepository) {
        this(null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), addRewardDto.getRewardBonusAmount(), 0.0, addRewardDto.getRemarks());

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();

        this.setCard(card);
    }

    public CashbackReward() {
    }

    public void updateBalance(Double rewardBasedAmount, CashbackRewardRepository cashbackRewardRepository) {
        CashbackReward previousPoints = cashbackRewardRepository.findTopByOrderByIdDesc().orElse(new CashbackReward());

        this.setBalance(previousPoints.getBalance() + rewardBasedAmount);
    }

}
