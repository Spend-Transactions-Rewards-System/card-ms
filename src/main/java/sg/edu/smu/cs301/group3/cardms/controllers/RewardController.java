package sg.edu.smu.cs301.group3.cardms.controllers;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.operations.TemplateAcknowledgementMode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.services.RewardService;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/card/rewards")
public class RewardController {
    @Value("${aws.sqs.queue.url}")
    private String endPoint;

    @Value("${aws.sqs.queue.name}")
    private String queueName;

    @Autowired
    SqsAsyncClient sqsAsyncClient;

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
    public ResponseEntity<String> sendMessage(@RequestBody String message){
        SqsTemplate template = SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(options -> options.acknowledgementMode(TemplateAcknowledgementMode.MANUAL))
                .build();
        SendResult<String> result = template.send(to -> to.queue(queueName)
                .payload(message)
        );
        return ResponseEntity.ok(result.message().toString());
    }



}
