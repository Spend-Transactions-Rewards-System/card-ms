package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.models.Customer;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    Card findByCardId(String cardId);

    List<Card> findAllByCustomer(Customer customer);
}
