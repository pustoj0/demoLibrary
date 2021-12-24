package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.ContentType;
import com.example.demolibrary.model.MessagingType;
import com.example.demolibrary.model.messagepostback.MessagePostbackDTO;
import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import com.example.demolibrary.model.senderaction.SenderActionDTO;
import com.example.demolibrary.model.senderaction.SenderActionType;
import com.example.demolibrary.model.sentquickreplymessage.*;
import com.example.demolibrary.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class SendQuickReplyMessageServiceImpl implements SendMessageService {
    private static final Logger logger = LoggerFactory.getLogger(SendQuickReplyMessageServiceImpl.class);

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
                                    SenderActionDTO senderActionDTO = new SenderActionDTO(
                                            new com.example.demolibrary.model.senderaction.Recipient(senderId),
                                            SenderActionType.typing_on.name());
                                    HttpEntity<SenderActionDTO> senderActionDTOHttpEntity = new HttpEntity<>(senderActionDTO, headers);
                                    restTemplate.exchange(messenger.getMessagesRequestURI(),
                                            HttpMethod.POST, senderActionDTOHttpEntity, Void.class);
                                    logger.info("HELLO WORLD1");
                                    try {
                                        TimeUnit.SECONDS.sleep(2);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    logger.info("HELLO WORLD1");
                                    senderActionDTO.setSender_action(SenderActionType.typing_off.name());
                                    senderActionDTOHttpEntity = new HttpEntity<>(senderActionDTO, headers);
                                    restTemplate.exchange(messenger.getMessagesRequestURI(),
                                            HttpMethod.POST, senderActionDTOHttpEntity, Void.class);
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
