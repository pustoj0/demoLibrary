package com.example.demolibrary.service.mapper;

import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import java.util.ArrayList;
import java.util.List;

public class MessageMapper {
    public List<String> getSenderIds(MessagePayloadDTO messagePayloadDTO) {
        List<String> senderIds = new ArrayList<>();
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String senderId = messaging.getSender().getId();
                                senderIds.add(senderId);
                            });
                });
        return senderIds;
    };

    public List<String> getMessageText(MessagePayloadDTO messagePayloadDTO) {
        List<String> messages = new ArrayList<>();
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String messageText = messaging.getMessage().getText();
                                messages.add(messageText);
                            });
                });
        return messages;
    }

    public List<String> getPostbackPayload(MessagePayloadDTO messagePayloadDTO) {
        List<String> payloads = new ArrayList<>();
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String payload = messaging.getPostback().getPayload();
                                payloads.add(payload);
                            });
                });
        return new ArrayList<>();
    }
}
