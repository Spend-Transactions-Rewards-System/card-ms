package sg.edu.smu.cs301.group3.cardms.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public abstract class Reward {

    private String tenant;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    private Double rewardBonusAmount;

    private Double balance;

    private String remarks;
}
