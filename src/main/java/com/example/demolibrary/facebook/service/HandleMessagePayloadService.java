package com.example.demolibrary.facebook.service;

import com.example.demolibrary.facebook.dto.receive.message.MessagePayloadDTO;

public interface HandleMessagePayloadService {
    void handle(MessagePayloadDTO messagePayloadDTO);
}
