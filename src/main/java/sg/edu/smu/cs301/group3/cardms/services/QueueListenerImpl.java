package sg.edu.smu.cs301.group3.cardms.services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import sg.edu.smu.cs301.group3.cardms.dtos.RewardDto;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class QueueListenerImpl implements QueueListener {
    Logger logger = LoggerFactory.getLogger(QueueListener.class);

    @Autowired
    RewardServiceImpl rewardServiceImpl;

    @Value("aws.campaign.to.card.queue.url")
    private String campaignToCardQueueUrl;

    @Autowired
    SqsAsyncClient sqsAsyncClient;

    @SqsListener(value = "${aws.campaign.to.card.queue}")
    private void receiveMessage(Message<AddRewardDto> message) {
            try{
                // call processMessage to insert record into Aurora DB
//                TimeUnit.SECONDS.sleep(1);
                processMessagePayload(message.getPayload());

//                 retrieve message receipt handle and send message delete request when message is processed
                String receiptHandle = (String) message.getHeaders().get("Sqs_ReceiptHandle");
                DeleteMessageRequest deleteRequest = DeleteMessageRequest.builder()
                        .queueUrl(campaignToCardQueueUrl)
                        .receiptHandle(receiptHandle)
                        .build();
                sqsAsyncClient.deleteMessage(deleteRequest);
            } catch (Exception e){
                e.printStackTrace();
                logger.error("Unable to process message" + message.getHeaders().get("Sqs_ReceiptHandle"));
            }
    }

    private void processMessagePayload(AddRewardDto payload){
        logger.info("sqs payload received: " + payload);
        RewardDto rewardDto = rewardServiceImpl.addEarnedReward(payload);
    }
}
