package sg.edu.smu.cs301.group3.cardms.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;
import sg.edu.smu.cs301.group3.cardms.models.RewardType;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CustomerRepository;

@Configuration
@Profile("dev")
public class CardSeeder {
    private final static Logger logger = LoggerFactory.getLogger(CardSeeder.class);
    private final CardRepository cardRepository;
    private final CustomerRepository customerRepository;
    public CardSeeder(CardRepository cardRepository, CustomerRepository customerRepository) {
        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
        insertTestData();
    }

    public void insertTestData() {
        logger.info("===========Inserting Card Test Data===========");

        Customer testCustomer01 = new Customer("scis","customer01", "tamus@gmail.com");
        customerRepository.save(testCustomer01);
        Card testCard01 = new Card( "card01", testCustomer01, "scis_premiummiles", RewardType.miles, "scis");
        Card testCard02 = new Card("card02", testCustomer01, "scis_platinummiles", RewardType.miles, "scis");
        cardRepository.save(testCard01);
        cardRepository.save(testCard02);


        Customer testCustomer02 = new Customer("scis","customer02", "kelvin@gmail.com");
        customerRepository.save(testCustomer02);
        Card testCard03 = new Card("card03", testCustomer02, "scis_premiummiles", RewardType.miles, "scis");
        Card testCard04 = new Card("card04", testCustomer02, "scis_platinummiles", RewardType.miles, "scis");
        cardRepository.save(testCard03);
        cardRepository.save(testCard04);

        Customer testCustomer03 = new Customer("scis","customer03", "joshua.zhangzy@gmail.com");
        customerRepository.save(testCustomer03);
        Card testCard05 = new Card("card05", testCustomer03, "scis_premiummiles", RewardType.miles, "scis");
        Card testCard06 = new Card("card06", testCustomer03, "scis_platinummiles", RewardType.miles, "scis");
        Card testCard07 = new Card("card07", testCustomer03, "scis_shopping", RewardType.points, "scis");
        Card testCard08 = new Card("card08", testCustomer03, "scis_freedom", RewardType.miles, "scis");
        Card testCard09 = new Card("605ec742-0ac4-43e7-8e3b-8bc63379a2fe", testCustomer03, "scis_shopping", RewardType.points, "scis");


        cardRepository.save(testCard05);
        cardRepository.save(testCard06);
        cardRepository.save(testCard07);
        cardRepository.save(testCard08);
        cardRepository.save(testCard09);

        logger.info("===========Test Data Inserted===========");
    }
}
