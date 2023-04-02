package sg.edu.smu.cs301.group3.cardms.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public ResponseEntity<String> healthcheck(){
        return new ResponseEntity<>("Health", HttpStatus.OK);
    }
}
