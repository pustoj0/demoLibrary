package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.facebook.Messenger;
import com.example.demolibrary.facebook.dto.DTO;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateDTO;
import com.example.demolibrary.facebook.service.SendMessageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendMessageServiceImpl implements SendMessageService {
    private RestTemplate restTemplate;
    private Messenger messenger;

    @Override
    public void sendMessage(DTO dto) {
        HttpEntity<DTO> entity = new HttpEntity<>(dto);
        restTemplate.exchange(messenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
    }
}
