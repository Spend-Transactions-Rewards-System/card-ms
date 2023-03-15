package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.CashbackReward;

@Repository
public interface CashbackRewardRepository extends RewardRepository<CashbackReward, Long> {

}
