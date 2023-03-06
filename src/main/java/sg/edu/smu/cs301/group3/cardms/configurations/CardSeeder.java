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
        Card testCard01 = new Card( "card01", testCustomer01, "SCIS_PremiumMiles", RewardType.miles, "scis");
        Card testCard02 = new Card("card02", testCustomer01, "SCIS_PlatinumMiles", RewardType.miles, "scis");
        cardRepository.save(testCard01);
        cardRepository.save(testCard02);


        Customer testCustomer02 = new Customer("scis","customer02", "kelvin@gmail.com");
        customerRepository.save(testCustomer02);
        Card testCard03 = new Card("card03", testCustomer02, "SCIS_PremiumMiles", RewardType.miles, "scis");
        Card testCard04 = new Card("card04", testCustomer02, "SCIS_PlatinumMiles", RewardType.miles, "scis");
        cardRepository.save(testCard03);
        cardRepository.save(testCard04);

        logger.info("===========Test Data Inserted===========");
    }
}
