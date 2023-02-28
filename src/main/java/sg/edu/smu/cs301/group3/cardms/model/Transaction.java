package sg.edu.smu.cs301.group3.cardms.model;

import java.util.Date;

public class Transaction {
    private String id;
    private String transactionId;
    private String merchant;
    private int mcc;
    private Currencies currency;
    private double amount;
    private Date transactionDate;
    private String cardId;
    private String cardPan;
    private Card cardType;
    private double rewardAmount;

    public Transaction(String id, String transactionId, String merchant, int mcc, Currencies currency, double amount, Date transactionDate, String cardId, String cardPan, Card cardType, double rewardAmount){
        this.id = id;
        this.transactionId = transactionId;
        this.merchant = merchant;
        this.mcc = mcc;
        this.currency = currency;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.cardId = cardId;
        this.cardPan = cardPan;
        this.cardType = cardType;
        this.rewardAmount = rewardAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public Integer getMcc() {
        return mcc;
    }

    public void setMcc(Integer mcc) {
        this.mcc = mcc;
    }

    public Currencies getCurrency() {
        return currency;
    }

    public void setCurrency(Currencies currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getCardPan() {
        return cardPan;
    }

    public void setCardPan(String cardPan) {
        this.cardPan = cardPan;
    }

    public Card getCardType() {
        return cardType;
    }

    public void setCardType(Card cardType) {
        this.cardType = cardType;
    }

    public Double getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount) {
        this.rewardAmount = rewardAmount;
    }
}
