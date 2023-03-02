package sg.edu.smu.cs301.group3.cardms.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;
import sg.edu.smu.cs301.group3.cardms.models.RewardType;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CustomerRepository;

@Configuration
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

        Customer testCustomer1 = new Customer("12345", "test@gmail.com");
        customerRepository.save(testCustomer1);
        Card testCard1 = new Card("98765", testCustomer1, "SCIS_PremiumMiles", RewardType.miles);
        Card testCard2 = new Card("54321", testCustomer1, "SCIS_PlatinumMiles", RewardType.miles);
        cardRepository.save(testCard1);
        cardRepository.save(testCard2);

        logger.info("===========Test Data Inserted===========");
    }
}
