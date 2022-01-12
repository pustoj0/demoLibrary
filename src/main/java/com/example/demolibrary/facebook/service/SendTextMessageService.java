package com.example.demolibrary.facebook.service;

import com.example.demolibrary.facebook.dto.send.textmessage.TextMessageDTO;

public interface SendTextMessageService {
    void sendTextMessage(TextMessageDTO textMessageDTO);
}
