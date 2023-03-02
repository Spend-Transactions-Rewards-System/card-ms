package sg.edu.smu.cs301.group3.cardms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.services.CardServiceImpl;

@RestController
@RequestMapping("/api/v1/card")
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
