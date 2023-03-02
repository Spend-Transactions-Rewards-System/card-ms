package sg.edu.smu.cs301.group3.cardms.service;

import org.springframework.http.converter.json.MappingJacksonValue;
import sg.edu.smu.cs301.group3.cardms.models.Card;

import java.util.List;

public interface CardService {
    MappingJacksonValue getCardsByCustomerId(String customerId);

}
