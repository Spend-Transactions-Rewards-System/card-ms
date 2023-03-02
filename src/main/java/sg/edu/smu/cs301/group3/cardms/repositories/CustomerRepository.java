package sg.edu.smu.cs301.group3.cardms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.edu.smu.cs301.group3.cardms.models.Customer;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    Optional<Customer> findById(String customerId);
}
