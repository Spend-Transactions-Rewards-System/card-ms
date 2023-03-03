package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;

@Builder
@Entity
public class Card implements Serializable {
    @Id
    private String cardId;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;
    private String cardType;
    @Enumerated(EnumType.STRING)
    private RewardType rewardType;

    public Card(){}
    public Card(String cardId, Customer customer, String cardType, RewardType rewardType) {
        this.cardId = cardId;
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

}
