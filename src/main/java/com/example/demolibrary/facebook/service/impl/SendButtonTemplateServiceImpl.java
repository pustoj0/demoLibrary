package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.facebook.Messenger;
import com.example.demolibrary.facebook.dto.send.template.button.ButtonTemplateDTO;
import com.example.demolibrary.facebook.service.SendButtonTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendButtonTemplateServiceImpl implements SendButtonTemplateService {
    private RestTemplate restTemplate;
    private Messenger messenger;

    @Override
    public void sendButtonTemplate(ButtonTemplateDTO buttonDTO) {
        HttpEntity<ButtonTemplateDTO> entity = new HttpEntity<>(buttonDTO);
        restTemplate.exchange(messenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
    }
}
