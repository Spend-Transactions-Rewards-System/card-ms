package sg.edu.smu.cs301.group3.cardms.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CampaignBonusAlertDto {

    private String email;
    private String spendDate;
    private String merchant;
    private String cardType;
    private String currency;
    private double amount;
    private double rewardAmount;
    private double balance;
    private String remarks;
}
