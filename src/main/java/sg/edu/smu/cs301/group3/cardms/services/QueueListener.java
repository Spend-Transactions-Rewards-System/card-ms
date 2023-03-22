package sg.edu.smu.cs301.group3.cardms.services;

import org.springframework.messaging.Message;
import sg.edu.smu.cs301.group3.cardms.dtos.AddRewardDto;

public interface QueueListener {
    private void receiveMessage(Message<AddRewardDto> message) {}

    private void processMessagePayload(AddRewardDto payload) {};
}
