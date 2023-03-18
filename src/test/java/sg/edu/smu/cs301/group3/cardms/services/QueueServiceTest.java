//package sg.edu.smu.cs301.group3.cardms.services;
//
//import org.springframework.context.annotation.Import;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasItems;
//
//@SpringBootTest
//@Import(SqsConfiguration.class)
//public class QueueServiceTest {
//    @Autowired
//    private SqsConfiguration sqsConfiguration;
//
//    @Value("${aws.sqs.queue.name}")
//    private String queueName;
//
//    @Autowired
//    private QueueListener queueListener;
//
//    @Autowired
//    private QueueService queueService;
//
//    @Test
//    public void testSQSIntegration() throws Exception {
//        // Send a message to the queue
//        String message = "Test message";
//        queueService.sendMessage(queueName, message);
//
//        // Wait for the message to be processed by the listener
//        Thread.sleep(5000);
//
//        // Verify that the message was consumed by the listener
//        System.out.println(queueListener.getMessages());
//        assertThat(queueListener.getMessages(), hasItems(message));
//    }
//}
