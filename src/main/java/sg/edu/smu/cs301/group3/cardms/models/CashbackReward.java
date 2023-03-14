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


    public CashbackReward(String tenant, Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double balance, String remarks, Long previousCashbackTransaction) {
        super(tenant, id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, balance, remarks, previousCashbackTransaction );
    }

    public CashbackReward(AddRewardDto addRewardDto, CardRepository cardRepository, CashbackRewardRepository cashbackRewardRepository) {
        this(addRewardDto.getTenant(), null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), 0.0, addRewardDto.getRemarks(), null);

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();
        CashbackReward previousCashbackReward = cashbackRewardRepository.findTopByCardOrderByIdDesc(card).get();

        updateBalance(previousCashbackReward.getBalance());

        this.setCard(card);
        this.setPreviousTransaction(previousCashbackReward.getId());
    }

    public CashbackReward() {
    }

    private void updateBalance(Double previousBalance) {
        this.setBalance(previousBalance + this.getRewardAmount());
    }

}
