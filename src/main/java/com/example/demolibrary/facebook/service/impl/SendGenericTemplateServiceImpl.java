package com.example.demolibrary.facebook.service.impl;

import com.example.demolibrary.facebook.Messenger;
import com.example.demolibrary.facebook.dto.send.template.generic.GenericTemplateDTO;
import com.example.demolibrary.facebook.service.SendGenericTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class SendGenericTemplateServiceImpl implements SendGenericTemplateService {
    private RestTemplate restTemplate;
    private Messenger messenger;

    @Override
    public void sendGenericTemplate(GenericTemplateDTO genericTemplateDTO) {
        HttpEntity<GenericTemplateDTO> entity = new HttpEntity<>(genericTemplateDTO);
        restTemplate.exchange(messenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
    }
}
