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
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class QueueListenerImpl {
    Logger logger = LoggerFactory.getLogger(QueueListenerImpl.class);

    @Value("${aws.listening.sqs.queue.name}")
    private String queueName;

    @Autowired
    SqsAsyncClient sqsAsyncClient;

    // Fix 3 threads, 1 for each reward type
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @SqsListener("SeanTestQueue")
    public void receiveMessage(String message) {
        executorService.submit(() -> {
            // call processMessage to insert record into Aurora DB
            processMessage(message);

            // acknowledge message processed

        });
    }

    public void processMessage(String message){
        logger.info("message received: " + message);
    }

    public void acknowledgeMessage(String message){
        SqsTemplate template = SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient)
                .configure(options -> options.acknowledgementMode(TemplateAcknowledgementMode.MANUAL))
                .build();
        SendResult<String> result = template.send(to -> to.queue(queueName)
                .payload(message)
        );

    }
}
