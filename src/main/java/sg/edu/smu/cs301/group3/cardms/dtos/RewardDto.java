package sg.edu.smu.cs301.group3.cardms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.smu.cs301.group3.cardms.models.Currencies;
import sg.edu.smu.cs301.group3.cardms.models.Reward;
import sg.edu.smu.cs301.group3.cardms.models.RewardType;
import sg.edu.smu.cs301.group3.cardms.services.TransactionType;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RewardDto {

    private Long id;

    private Date transactionDate;

    private String description;

    private String type;

    private Double change;

    private Double balance;

    private String card;

    private String remarks;

    private RewardType rewardType;

    private Currencies currency;

    private Double amount;

    public RewardDto(Reward reward) {
        this.id = reward.getId();
        this.transactionDate = reward.getTransactionDate();
        this.description = reward.getMerchant();
        this.type = TransactionType.Earn.name();
        this.change = reward.getRewardAmount();
        this.balance = Math.round(reward.getBalance() * 100.0) /100.0;
        this.card = reward.getCard().getCardType();
        this.remarks = reward.getRemarks();
        this.rewardType = reward.getCard().getRewardType();
        this.currency = reward.getCurrency();
        this.amount = reward.getAmount();
    }
}
