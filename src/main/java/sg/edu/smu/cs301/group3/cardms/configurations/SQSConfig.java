package sg.edu.smu.cs301.group3.cardms.configurations;

import io.awspring.cloud.autoconfigure.sqs.SqsProperties;
import io.awspring.cloud.sqs.config.SqsBootstrapConfiguration;
import io.awspring.cloud.sqs.config.SqsMessageListenerContainerFactory;
import io.awspring.cloud.sqs.listener.ListenerMode;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementCallback;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementOrdering;
import io.awspring.cloud.sqs.listener.acknowledgement.AcknowledgementResultCallback;
import io.awspring.cloud.sqs.listener.acknowledgement.handler.AcknowledgementMode;
import io.awspring.cloud.sqs.operations.SqsOperations;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.Message;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;

import java.time.Duration;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;

@Import(SqsBootstrapConfiguration.class)
@Configuration
public class SQSConfig {

    //Some possible implementation if required any customization for SQS
    @Bean
    public SqsMessageListenerContainerFactory<Object> defaultSqsListenerContainerFactory() {


        return SqsMessageListenerContainerFactory
                .builder()
                .configure(options -> options
                        .acknowledgementMode(AcknowledgementMode.ON_SUCCESS)
                        .acknowledgementOrdering(AcknowledgementOrdering.ORDERED)
                        .acknowledgementThreshold(1)
                        .acknowledgementInterval(Duration.ZERO)
                        .listenerMode(ListenerMode.SINGLE_MESSAGE))
                .sqsAsyncClient(sqsAsyncClient())
                .build();
    }

//    @Bean
//    public AcknowledgementCallback<AddRewardDto> getAcknowledgementResultCallback() {
//
//       return  new AcknowledgementResultCallback();
//    }
//
//
//
//    class AcknowledgementResultCallback implements AcknowledgementCallback<AddRewardDto> {
//        @Override
//        public CompletableFuture<Void> onAcknowledge(Message<AddRewardDto> message) {
//            return AcknowledgementCallback.super.onAcknowledge(message);
//        }
//
//        @Override
//        public CompletableFuture<Void> onAcknowledge(Collection<Message<AddRewardDto>> messages) {
//            return AcknowledgementCallback.super.onAcknowledge(messages);
//        }
//    }

    @Bean
    public SqsProperties.Listener listener() {
        return new SqsProperties.Listener();
    }

    @Bean
    public SqsAsyncClient sqsAsyncClient() {
        return SqsAsyncClient.builder().build();
    }

    @Primary
    @Bean
    public SqsOperations sqsOperations() {
        return SqsTemplate.builder()
                .sqsAsyncClient(sqsAsyncClient())
                .configureDefaultConverter(sqsMessagingMessageConverter -> {
                    sqsMessagingMessageConverter.setPayloadTypeHeader("my-dto-type-header");
                })
                .buildSyncTemplate();
    }



}
