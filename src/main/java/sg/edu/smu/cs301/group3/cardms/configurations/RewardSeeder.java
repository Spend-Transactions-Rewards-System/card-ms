package sg.edu.smu.cs301.group3.cardms.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import sg.edu.smu.cs301.group3.cardms.models.Reward;
import sg.edu.smu.cs301.group3.cardms.repositories.RewardRepository;

@Configuration
public class RewardSeeder {

    private final static Logger logger = LoggerFactory.getLogger(RewardSeeder.class);
    private final RewardRepository rewardRepository;
    public RewardSeeder(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
        insertTestData();
    }

    public void insertTestData() {
        logger.info("===========Inserting Reward Test Data===========");
        Reward testReward1 = new Reward();
        rewardRepository.save(testReward1);

        logger.info("===========Test Data Inserted===========");
    }
}
