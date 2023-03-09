package sg.edu.smu.cs301.group3.cardms.controllers;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.dtos.CardDto;
import sg.edu.smu.cs301.group3.cardms.services.CardServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card/cards")
public class CardController {
    @Autowired
    CardServiceImpl cardService;
    @CrossOrigin(origins = "*")
    @GetMapping("/{tenant}/{customerId}")
    public ResponseEntity<List<CardDto>> getCardByCustomerId(@PathVariable("tenant") String tenant, @PathVariable String customerId) {
        List<CardDto> response = cardService.getCardsByCustomerId(tenant, customerId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/healthcheck")
    public ResponseEntity<String> healthcheck(){
        return new ResponseEntity<>("Healthy", HttpStatus.OK);
    }

}
