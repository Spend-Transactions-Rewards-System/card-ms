package sg.edu.smu.cs301.group3.cardms.services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import io.awspring.cloud.sqs.operations.TemplateAcknowledgementMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.configurations.AwsSQSConfig;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import sg.edu.smu.cs301.group3.cardms.models.Reward;
import sg.edu.smu.cs301.group3.cardms.repositories.*;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class QueueListenerImpl {
    Logger logger = LoggerFactory.getLogger(QueueListenerImpl.class);

    @Autowired
    RewardServiceImpl rewardServiceImpl;

    @Value("${aws.card.to.campaign.queue}")
    private String cardToCampaign;

    @Autowired
    SqsAsyncClient sqsAsyncClient;

    // Fix 3 threads, 1 for each reward type
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @SqsListener(value = "${aws.campaign.to.card.queue}")
    public void receiveMessage(Message<AddRewardDto> message) {
        executorService.submit(() -> {
            // call processMessage to insert record into Aurora DB
            processMessagePayload(message.getPayload());

            // acknowledge message processed

        });
    }

    public void processMessagePayload(AddRewardDto payload){
        logger.info("payload received: " + payload);
        RewardDto rewardDto = rewardServiceImpl.addEarnedReward(payload);
        System.out.println(rewardDto.toString());
    }

    public void acknowledgeMessage(String message){
        SqsTemplate template = SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(options -> options.acknowledgementMode(TemplateAcknowledgementMode.MANUAL))
                .build();
        SendResult<String> result = template.send(to -> to.queue(cardToCampaign)
                .payload(message)
        );

    }
}
