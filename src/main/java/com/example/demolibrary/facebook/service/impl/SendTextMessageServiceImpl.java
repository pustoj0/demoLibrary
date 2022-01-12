package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.facebook.Messenger;
import com.example.demolibrary.facebook.dto.send.textmessage.TextMessageDTO;
import com.example.demolibrary.facebook.service.SendTextMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendTextMessageServiceImpl implements SendTextMessageService {
    private RestTemplate restTemplate;
    private Messenger messenger;

    @Override
    public void sendTextMessage(TextMessageDTO textMessageDTO) {
        HttpEntity<TextMessageDTO> entity = new HttpEntity<>(textMessageDTO);
        restTemplate.exchange(messenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
    }
}
