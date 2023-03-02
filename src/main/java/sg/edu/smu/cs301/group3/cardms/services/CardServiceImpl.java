package sg.edu.smu.cs301.group3.cardms.services;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public MappingJacksonValue getCardsByCustomerId(String customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("cardId");
            FilterProvider filterProvider = new SimpleFilterProvider().addFilter("cardFilter", simpleBeanPropertyFilter);

            List<Card> cards = cardRepository.findAllByCustomer(customer.get());

            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(cards);
            mappingJacksonValue.setFilters(filterProvider);
            return mappingJacksonValue;
        } else {
            throw new NullPointerException("Customer not found");
        }

    }

}
