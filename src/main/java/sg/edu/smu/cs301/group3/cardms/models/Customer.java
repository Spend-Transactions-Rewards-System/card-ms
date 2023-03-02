package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;
@Entity
public class Customer implements Serializable {
    @Id
    private String customerId;
    private String email;
    @OneToMany(mappedBy = "customer", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Card> cards;

    protected Customer(){}
    public Customer(String customerId, String email) {
        this.customerId = customerId;
        this.email = email;
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
}
