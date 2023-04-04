package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.PointsRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.RewardRepository;

import java.sql.Date;
import java.text.DecimalFormat;

@NoArgsConstructor
@Entity
@Data
public class PointsReward extends Reward {


    public PointsReward(String tenant, Long id, String transactionId, Card card, String merchant, Integer mcc, Currencies currency, Double amount, Date transactionDate, Double rewardAmount, Double balance, String remarks, Long previousPointsTransaction, Customer customer) {
        super(tenant, id, transactionId, card, merchant, mcc, currency, amount, transactionDate, rewardAmount, balance, remarks, previousPointsTransaction, customer);
    }

    public PointsReward(AddRewardDto addRewardDto, CardRepository cardRepository, RewardRepository rewardRepository) {
        super(addRewardDto, cardRepository, rewardRepository);
    }

    @Override
    protected void updateBalance(Double previousBalance) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(0);

        Double tempRewardAmount  =  Double.parseDouble(df.format(getRewardAmount()));

        this.setRewardAmount(tempRewardAmount);

        this.setBalance(previousBalance + tempRewardAmount);
    }

}
