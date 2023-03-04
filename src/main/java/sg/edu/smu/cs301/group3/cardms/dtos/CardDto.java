package sg.edu.smu.cs301.group3.cardms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sg.edu.smu.cs301.group3.cardms.models.Card;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {

    private String cardId;
    private String cardType;

    public CardDto(Card card) {
        this.cardId = card.getCardId();
        this.cardType = card.getCardType();
    }
}
