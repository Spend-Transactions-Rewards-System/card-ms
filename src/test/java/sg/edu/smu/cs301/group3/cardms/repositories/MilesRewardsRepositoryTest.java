package sg.edu.smu.cs301.group3.cardms.repositories;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import sg.edu.smu.cs301.group3.cardms.configurations.CardSeeder;
import sg.edu.smu.cs301.group3.cardms.configurations.RewardSeeder;
import sg.edu.smu.cs301.group3.cardms.helper.DateHelper;
import sg.edu.smu.cs301.group3.cardms.models.*;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MilesRewardsRepositoryTest {

    @Autowired
    MilesRewardRepository milesRewardRepository;

    @Autowired
    PointsRewardRepository pointsRewardRepository;

    @Autowired
    CashbackRewardRepository cashbackRewardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @BeforeAll
    public void setup() throws ParseException {
        CardSeeder cardSeeder = new CardSeeder(cardRepository, customerRepository);
        cardSeeder.insertTestData();

        RewardSeeder rewardSeeder = new RewardSeeder(milesRewardRepository, cardRepository);
        rewardSeeder.insertTestData();
    }

    @Test
    public void findAllByCard() throws ParseException {
        //arrange
        Card customer01_card01 = cardRepository.findByCardId("card01").get();

        MilesReward expectedMilesReward = new MilesReward(1L, "trans01", customer01_card01, "merchant01", 11111, Currencies.SGD, 10.0,
                new Date(DateHelper.dateFormat().parse("01/03/2023").getTime()), 14.0, 0.0, 14.0, "Base 1.4 Miles/SGD");

        //act
        List<MilesReward> result = milesRewardRepository.findAllByCard(customer01_card01);

        //assert
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0)).usingRecursiveComparison().isEqualTo(expectedMilesReward);
    }
}
