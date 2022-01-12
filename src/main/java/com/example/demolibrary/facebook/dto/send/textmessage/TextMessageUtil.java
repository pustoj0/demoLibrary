package com.example.demolibrary.facebook.dto.send.textmessage;

import com.example.demolibrary.facebook.dto.send.MessagingType;

public class TextMessageUtil {
    public static TextMessageDTO createTextMessage(String recipientId, String message) {
        TextMessageDTO textMessageDTO = new TextMessageDTO();
        textMessageDTO.setRecipient(new Recipient(recipientId));
        textMessageDTO.setMessaging_type(MessagingType.RESPONSE.name());
        textMessageDTO.setMessage(new Message(message));
        return textMessageDTO;
    }
}
