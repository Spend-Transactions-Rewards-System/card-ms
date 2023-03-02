package sg.edu.smu.cs301.group3.cardms.controllers;

import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;

import java.util.List;

public interface RewardsOps {
    RewardDto addEarnedReward(RewardDto rewardDto);
    List<RewardDto> getEarnedRewards(String customerId);
    RewardDto getEarnedReward(String customerId, CardType cardType);

}
