package sg.edu.smu.cs301.group3.cardms.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transactionId;

    @ManyToOne
    private Card card;

    private String merchant;

    private Integer mcc;

    @Enumerated(EnumType.STRING)
    private Currencies currency;

    private Double amount;

    private Date transactionDate;

    private Double rewardAmount;

    private Double rewardBonusAmount;

    private Double balance;

    private String remarks;
}
