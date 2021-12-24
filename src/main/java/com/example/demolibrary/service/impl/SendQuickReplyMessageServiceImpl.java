package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.ContentType;
import com.example.demolibrary.model.MessagingType;
import com.example.demolibrary.model.messagepostback.MessagePostbackDTO;
import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import com.example.demolibrary.model.sentquickreplymessage.*;
import com.example.demolibrary.service.SendMessageService;
import com.example.demolibrary.service.SendSenderActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class SendQuickReplyMessageServiceImpl implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(SendQuickReplyMessageServiceImpl.class);

    private RestTemplate restTemplate;
    private MyMessenger messenger;
    private SendSenderActionService actionService;

    public SendQuickReplyMessageServiceImpl(RestTemplate restTemplate, MyMessenger messenger, SendSenderActionService actionService) {
        this.restTemplate = restTemplate;
        this.messenger = messenger;
        this.actionService = actionService;
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
                                String text = messaging.getMessage().getText();
                                if (text.equals("Ukraine")) {
                                    String messageText = "Choose Country";
                                    Recipient recipient = new Recipient(senderId);
                                    QuickReply quickReply1 = new QuickReply(ContentType.text.name(),
                                            "Ukraine", "1111111111", "");
                                    QuickReply quickReply2 = new QuickReply(ContentType.text.name(),
                                            "Italy", "2222222222", "");
                                    List<QuickReply> quickReplies = new ArrayList<>();
                                    quickReplies.add(quickReply1);
                                    quickReplies.add(quickReply2);
                                    Message message = new Message(messageText, quickReplies);
                                    SendQuickReplyDTO sendQuickReplyDTO = new SendQuickReplyDTO(recipient,
                                            MessagingType.RESPONSE.name(), message);
                                    HttpEntity<SendQuickReplyDTO> entity = new HttpEntity<>(sendQuickReplyDTO, headers);
                                    restTemplate.exchange(messenger.getMessagesRequestURI(),
                                            HttpMethod.POST, entity, MessagePostbackDTO.class);

                                }
                            });
                });
    }
}
