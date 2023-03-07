package sg.edu.smu.cs301.group3.cardms.services;

import sg.edu.smu.cs301.group3.cardms.dtos.CardDto;

import java.util.List;

public interface CardService {
    List<CardDto> getCardsByCustomerId(String tenant, String customerId);

}
