package sg.edu.smu.cs301.group3.cardms.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.*;
import sg.edu.smu.cs301.group3.cardms.repositories.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.Arrays;

@Configuration
@DependsOn("cardSeeder")
@Profile("dev")
public class RewardSeeder {

    private final static Logger logger = LoggerFactory.getLogger(RewardSeeder.class);

    private final MilesRewardRepository milesRewardRepository;

    private final PointsRewardRepository pointsRewardRepository;

    private final CashbackRewardRepository cashbackRewardRepository;
    private final CardRepository cardRepository;
    public RewardSeeder(MilesRewardRepository milesRewardRepository, PointsRewardRepository pointsRewardRepository, CashbackRewardRepository cashbackRewardRepository, CardRepository cardRepository) {
        this.milesRewardRepository = milesRewardRepository;
        this.pointsRewardRepository = pointsRewardRepository;
        this.cashbackRewardRepository = cashbackRewardRepository;
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
        Card customer03_card05 = cardRepository.findByCardId("card05").get();
        Card customer03_card06 = cardRepository.findByCardId("card06").get();
        Card customer03_card07 = cardRepository.findByCardId("card07").get();
        Card customer03_card08 = cardRepository.findByCardId("card08").get();

         MilesReward milesReward01_testCustomer01_card01 =  new MilesReward("scis", 1L,"trans01", customer01_card01, "merchant01", 11111, Currencies.SGD, 100.0,
                 new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 14.0,  "Base 1.4 Miles/SGD", new MilesReward());

         MilesReward milesReward02_testCustomer02_card03 = new MilesReward("scis", 2L,"trans02", customer02_card03, "merchant02", 22222, Currencies.SGD, 100.0,
                 new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 28.0,  "Base 1.4 Miles/SGD", new MilesReward());

        MilesReward milesReward03_testCustomer03_card05 = new MilesReward("scis", 3L,"trans03", customer03_card05, "Mandai Zoo", 33333, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 14.0,  "Base 1.4 Miles/SGD", new MilesReward());

        MilesReward milesReward04_testCustomer03_card06 = new MilesReward("scis", 4L,"trans04", customer03_card06, "Sushiro", 22222, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 28.0,  "Base 1.4 Miles/SGD", new MilesReward());

        MilesReward milesReward05_testCustomer02_card03 = new MilesReward("scis", 5L,"trans04", customer02_card03, "Sushiro", 22222, Currencies.SGD, 1000.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),140.0, 168.0,  "Base 1.4 Miles/SGD", new MilesReward());

        MilesReward milesReward06_testCustomer03_card06 = new MilesReward("scis", 6L,"trans04", customer03_card06, "Sushiro", 22222, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),14.0, 42.0,  "Base 1.4 Miles/SGD", new MilesReward());

        PointsReward pointsReward01_testCustomer03_card07 = new PointsReward("scis", 1L,"trans05", customer03_card07, "H&M", 44444, Currencies.SGD, 100.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),100.0, 100.0,  "1 point/SGD on spend", new PointsReward());

        CashbackReward cashbackReward01_testCustomer03_card08 = new CashbackReward("scis", 1L,"trans06", customer03_card08, "Uniqlo", 22222, Currencies.SGD, 500.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()),5.0, 5.0,  "1.0% cashback for all spend", new CashbackReward());

        milesRewardRepository.saveAllAndFlush(Arrays.asList(milesReward01_testCustomer01_card01, milesReward02_testCustomer02_card03, milesReward03_testCustomer03_card05,
                milesReward04_testCustomer03_card06, milesReward05_testCustomer02_card03, milesReward06_testCustomer03_card06));

        pointsRewardRepository.saveAllAndFlush(Arrays.asList(pointsReward01_testCustomer03_card07));

        cashbackRewardRepository.saveAllAndFlush(Arrays.asList(cashbackReward01_testCustomer03_card08));

        logger.info("===========Test Data Inserted===========");
    }
}
