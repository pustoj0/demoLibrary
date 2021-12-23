package com.example.demolibrary.service.impl;


import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.ContentType;
import com.example.demolibrary.model.MessagingType;
import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import com.example.demolibrary.model.sentmessage.SendMessageDTO;
import com.example.demolibrary.model.sentquickreplymessage.*;
import com.example.demolibrary.service.SendMessageService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SendQuickReplyMessageServiceImpl implements SendMessageService {
    private RestTemplate restTemplate;
    private MyMessenger messenger;

    public SendQuickReplyMessageServiceImpl(RestTemplate restTemplate, MyMessenger messenger) {
        this.restTemplate = restTemplate;
        this.messenger = messenger;
    }
    @Override
    public void sendMessage(MessagePayloadDTO messagePayloadDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String senderId = messaging.getSender().getId();
                                String messageText = "Choose Country";
                                Recipient recipient = new Recipient(senderId);
                                QuickReply quickReply1 = new QuickReply(ContentType.text.name(),
                                        "Ukraine", "1", "");
                                QuickReply quickReply2 = new QuickReply(ContentType.text.name(),
                                        "Italy", "2", "");
                                List<QuickReply> quickReplies = new ArrayList<>();
                                quickReplies.add(quickReply1);
                                quickReplies.add(quickReply2);
                                Message message = new Message(messageText, quickReplies);
                                SendQuickReplyDTO sendQuickReplyDTO = new SendQuickReplyDTO(recipient,
                                        MessagingType.RESPONSE.name(), message);
                                HttpEntity<SendQuickReplyDTO> entity = new HttpEntity<>(sendQuickReplyDTO, headers);
                                ResponseEntity<QuickReplyResponseDTO> responseEntity
                                        = restTemplate.exchange(messenger.getMessagesRequestURI(),
                                        HttpMethod.POST, entity, QuickReplyResponseDTO.class);
                                System.out.println(Objects.requireNonNull(responseEntity.getBody()).getMessage_id());
                            });
                });
    }
}
