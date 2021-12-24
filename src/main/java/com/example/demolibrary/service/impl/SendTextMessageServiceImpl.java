package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.MessagingType;
import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import com.example.demolibrary.model.senderaction.SenderActionType;
import com.example.demolibrary.model.sentmessage.Message;
import com.example.demolibrary.model.sentmessage.Recipient;
import com.example.demolibrary.model.sentmessage.SendMessageDTO;
import com.example.demolibrary.service.SendMessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SendTextMessageServiceImpl implements SendMessageService {
    private RestTemplate restTemplate;
    private MyMessenger messenger;

    public SendTextMessageServiceImpl(RestTemplate restTemplate, MyMessenger messenger) {
        this.restTemplate = restTemplate;
        this.messenger = messenger;
    }

    @Override
    public void sendMessage(MessagePayloadDTO messagePayloadDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println(messagePayloadDTO);
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String senderId = messaging.getSender().getId();
                                String messageText = messaging.getMessage().getText();
                                Recipient recipient = new Recipient(senderId);
                                Message message = new Message(messageText);
                                String sender_action = SenderActionType.typing_on.name();
                                SendMessageDTO sendMessageDTO
                                        = new SendMessageDTO(MessagingType.RESPONSE.name(), recipient, message, sender_action);
                                HttpEntity<SendMessageDTO> entity = new HttpEntity<>(sendMessageDTO, headers);
                                restTemplate.exchange(messenger.getMessagesRequestURI(),
                                        HttpMethod.POST, entity, Void.class);
                            });
                });
    }
}
