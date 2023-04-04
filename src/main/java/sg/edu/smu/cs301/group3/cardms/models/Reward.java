package sg.edu.smu.cs301.group3.cardms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.repositories.CardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.MilesRewardRepository;
import sg.edu.smu.cs301.group3.cardms.repositories.RewardRepository;

import java.sql.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Reward {

    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String transactionId;

    @ManyToOne
    private Card card;

    @NotNull
    private String merchant;

    @NotNull
    private Integer mcc;

    @Enumerated(EnumType.STRING)
    private Currencies currency;

    @NotNull
    private Double amount;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date transactionDate;

    private Double rewardAmount;

    private Double balance;

    private String remarks;

    private Long previousTransaction;

    @ManyToOne
    private Customer customer;
    public Reward(AddRewardDto addRewardDto, CardRepository cardRepository, RewardRepository rewardRepository) {

        this(addRewardDto.getTenant(), null, addRewardDto.getTransactionId(), null, addRewardDto.getMerchant(), addRewardDto.getMcc(), addRewardDto.getCurrency(), addRewardDto.getAmount(),
                addRewardDto.getTransactionDate(), addRewardDto.getRewardAmount(), 0.0, addRewardDto.getRemarks(), null, null);

        Card card = cardRepository.findByCardId(addRewardDto.getCardId()).get();

//        Reward previousReward = (Reward) rewardRepository.findTopByCardOrderByIdDesc(card).orElseGet(() -> null);
        Reward previousReward = (Reward) rewardRepository.findTopByCustomerOrderByIdDesc(card.getCustomer()).orElseGet(() -> null);

        if(previousReward == null) {
            updateBalance(0.0);
        } else {
            updateBalance(previousReward.getBalance());
            this.setPreviousTransaction(previousReward.getId());
        }

        this.setCard(card);
        this.setCustomer(card.getCustomer());
    }

    protected abstract void updateBalance(Double previousBalance);
}
