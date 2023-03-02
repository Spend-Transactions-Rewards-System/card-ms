package sg.edu.smu.cs301.group3.cardms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.models.Card;
import sg.edu.smu.cs301.group3.cardms.service.CardService;
import sg.edu.smu.cs301.group3.cardms.service.CardServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardServiceImpl cardService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{customerId}")
    public ResponseEntity getCardByCustomerId(@PathVariable String customerId) {
        MappingJacksonValue response = cardService.getCardsByCustomerId(customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
