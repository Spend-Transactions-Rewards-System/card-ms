package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
@Builder
@Entity
@Data
@AllArgsConstructor
//@Transactional
public class Customer implements Serializable {

    private String tenant;
    @Id
    private String customerId;
    private String email;
    @OneToMany(mappedBy = "customer", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

    public Customer(){}
    public Customer(String tenant, String customerId, String email) {
        this.tenant = tenant;
        this.customerId = customerId;
        this.email = email;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return customerId.equals(customer.customerId) && email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, email);
    }
}
