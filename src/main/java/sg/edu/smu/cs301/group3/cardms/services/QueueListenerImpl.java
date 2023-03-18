package sg.edu.smu.cs301.group3.cardms.services;

import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import sg.edu.smu.cs301.group3.cardms.controllers.RewardController;

@Service
public class QueueListenerImpl {
    Logger logger = LoggerFactory.getLogger(QueueListenerImpl.class);

//    @SqsListener("SeanTestQueue")
//    public void handleMessage(String message){
//        logger.info("message from sqs queue {}", message);
//    }

    @SqsListener(value = "SeanTestQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message,
                               @Header("SenderId") String senderId) {
        logger.info("message received {} {}",senderId,message);
    }
}
