package sg.edu.smu.cs301.group3.cardms.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Currencies;
import sg.edu.smu.cs301.group3.cardms.models.MilesReward;
import sg.edu.smu.cs301.group3.cardms.models.Reward;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.MilesRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.RewardRepository;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

@Configuration
@DependsOn("cardSeeder")
@Profile("dev")
public class RewardSeeder {

    private final static Logger logger = LoggerFactory.getLogger(RewardSeeder.class);

    private final MilesRewardRepository milesRewardRepository;
    private final CardRepository cardRepository;
    public RewardSeeder(MilesRewardRepository milesRewardRepository, CardRepository cardRepository) {
        this.milesRewardRepository = milesRewardRepository;
        this.cardRepository = cardRepository;

        try {
            insertTestData();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertTestData() throws ParseException {
        logger.info("===========Inserting Reward Test Data===========");

        Card customer01_card01 = cardRepository.findByCardId("card01").get();
        Card customer02_card03 = cardRepository.findByCardId("card03").get();

         MilesReward milesReward01_testCustomer01_card01 =  new MilesReward("scis", 1L,"trans01", customer01_card01, "merchant01", 11111, Currencies.SGD, 10.0,
                 new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 0.0, 14.0,  "Base 1.4 Miles/SGD");

         MilesReward milesReward02_testCustomer02_card03 = new MilesReward("scis", 2L,"trans02", customer02_card03, "merchant02", 22222, Currencies.SGD, 10.0,
                 new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 0.0, 28.0,  "Base 1.4 Miles/SGD");

        milesRewardRepository.saveAllAndFlush(Arrays.asList(milesReward01_testCustomer01_card01, milesReward02_testCustomer02_card03));

        logger.info("===========Test Data Inserted===========");
    }
}
