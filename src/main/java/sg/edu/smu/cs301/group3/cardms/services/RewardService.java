package sg.edu.smu.cs301.group3.cardms.services;

import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;

import java.util.List;

public interface RewardService {
    RewardDto addEarnedReward(AddRewardDto addRewardDto);
    List<RewardDto> getCustomerEarnedRewards(String tenant, String customerId);
    List<RewardDto> getCardEarnedRewards(String tenant, String customerId, String cardType);
}
