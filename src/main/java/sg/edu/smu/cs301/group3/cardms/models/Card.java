package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Entity
public class Card implements Serializable {
    @Id
    private String cardId;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    private String cardType;
    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    private String tenant;

    public Card(){}
    public Card(String cardId, Customer customer, String cardType, RewardType rewardType, String tenant) {
        this.cardId = cardId;
        this.tenant = tenant;
        this.customer = customer;
        this.cardType = cardType;
        this.rewardType = rewardType;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public RewardType getRewardType() {
        return rewardType;
    }

    public void setRewardType(RewardType rewardType) {
        this.rewardType = rewardType;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(cardId, card.cardId) && Objects.equals(customer, card.customer) && Objects.equals(cardType, card.cardType) && rewardType == card.rewardType && Objects.equals(tenant, card.tenant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, customer, cardType, rewardType, tenant);
    }
}
