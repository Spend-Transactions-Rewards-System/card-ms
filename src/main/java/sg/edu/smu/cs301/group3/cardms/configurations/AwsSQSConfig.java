package sg.edu.smu.cs301.group3.cardms.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSQSConfig {
//    @Value("aws.sqs.queue.url")
//    private String url;
//    @Value("cloud.aws.region.static")
//    private String region;
//    @Value("aws_access_key_id")
//    private String awsAccessKey;
//    @Value("aws_secret_access_key")
//    private String awsSecretKey;


//    private AWSCredentialsProvider awsCredentialsProvider(){
//        return new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
//    }

//    @Bean
//    public QueueMessagingTemplate queueMessagingTemplate(){
//        return new QueueMessagingTemplate(amazonSQSAsync());
//    }
//
//    @Primary
//    @Bean
//    public AmazonSQSAsync amazonSQSAsync(){
//        return AmazonSQSAsyncClientBuilder
//                .standard()
//                .withRegion(region)
//                .withCredentials(awsCredentialsProvider())
//                .build();
//    }
//
//    @Bean
//    public SimpleMessageListenerContainerFactory simpleMessageListenerContainerFactory() {
//        SimpleMessageListenerContainerFactory msgListenerContainerFactory = new SimpleMessageListenerContainerFactory();
//        msgListenerContainerFactory.setAmazonSqs(amazonSQSAsync());
//        return msgListenerContainerFactory;
//    }
}
