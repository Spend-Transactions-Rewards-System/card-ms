package sg.edu.smu.cs301.group3.cardms.services;

import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class QueueListenerImpl {
    Logger logger = LoggerFactory.getLogger(QueueListenerImpl.class);

//    @SqsListener("SeanTestQueue")
//    public void handleMessage(String message){
//        logger.info("message from sqs queue {}", message);
//    }

    @SqsListener("SeanTestQueue")
    public void receiveMessage(String message) {
        logger.info("message received: " + message);
    }
}
