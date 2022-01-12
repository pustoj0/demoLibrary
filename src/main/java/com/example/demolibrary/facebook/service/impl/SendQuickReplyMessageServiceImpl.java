package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.facebook.Messenger;
import com.example.demolibrary.facebook.dto.send.quickreply.QuickReplyDTO;
import com.example.demolibrary.facebook.service.SendQuickReplyMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendQuickReplyMessageServiceImpl implements SendQuickReplyMessageService {
    private RestTemplate restTemplate;
    private Messenger messenger;

    @Override
    public void sendQuickReplyMessage(QuickReplyDTO quickReplyDTO) {
        HttpEntity<QuickReplyDTO> entity = new HttpEntity<>(quickReplyDTO);
        restTemplate.exchange(messenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
    }
}
