package sg.edu.smu.cs301.group3.cardms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.dtos.CardDto;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public List<CardDto> getCardsByCustomerId(String customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){
            List<Card> cards = cardRepository.findAllByCustomer(customer.get());
            List<CardDto> result = new ArrayList<>();
            cards.stream().forEach(card -> {
                result.add(new CardDto(card));
            });
            return result;
        } else {
            throw new NullPointerException("Customer not found");
        }

    }

}
