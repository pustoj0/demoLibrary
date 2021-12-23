package com.example.demolibrary.service;

import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;

public interface SendMessageService {
    void sendMessage(MessagePayloadDTO messagePayloadDTO);
}
