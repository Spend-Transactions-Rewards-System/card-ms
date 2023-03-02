package sg.edu.smu.cs301.group3.cardms.services;

import org.springframework.http.converter.json.MappingJacksonValue;

public interface CardService {
    MappingJacksonValue getCardsByCustomerId(String customerId);

}
