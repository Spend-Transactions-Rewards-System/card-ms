package sg.edu.smu.cs301.group3.cardms.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.smu.cs301.group3.cardms.models.Currencies;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddRewardDto {

    private String tenant;

    private String transactionId;

    private Date transactionDate;

    private String cardId;

    private String merchant;

    private Integer mcc;

    private Currencies currency;

    private Double amount;

    private Double rewardAmount;

    private String remarks;

}
