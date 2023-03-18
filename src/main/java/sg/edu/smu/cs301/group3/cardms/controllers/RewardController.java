package sg.edu.smu.cs301.group3.cardms.controllers;

import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.services.RewardService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/card/rewards")
public class RewardController {
    @Value("${aws.sqs.queue.url}")
    private String endPoint;
    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    Logger logger = LoggerFactory.getLogger(RewardController.class);

    private final RewardService rewardService;

    @GetMapping("/{tenant}/{customerId}")
    public ResponseEntity<List<RewardDto>> getCustomerRewards(@PathVariable("tenant") String tenant, @PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(rewardService.getCustomerEarnedRewards(tenant, customerId));
    }

    @GetMapping("/{tenant}/{customerId}/{cardType}")
    public ResponseEntity<List<RewardDto>> getCustomerRewardsByCard(@PathVariable("tenant") String tenant, @PathVariable("customerId") String customerId, @PathVariable("cardType") String cardType) {
        return ResponseEntity.ok(rewardService.getCardEarnedRewards(tenant, customerId, cardType));
    }

    @PostMapping("/message")
    public String sendMessage(@RequestBody String message) {

        try {
            queueMessagingTemplate.send(endPoint, MessageBuilder.withPayload(message).build());
            System.out.println("Message sent successfully  " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }



}
