package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.senderaction.Recipient;
import com.example.demolibrary.model.senderaction.SenderActionDTO;
import com.example.demolibrary.model.senderaction.SenderActionType;
import com.example.demolibrary.service.SendSenderActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class SendSenderActionServiceImpl implements SendSenderActionService {

    private static final Logger logger = LoggerFactory.getLogger(SendSenderActionServiceImpl.class);

    private RestTemplate restTemplate;
    private MyMessenger messenger;

    public SendSenderActionServiceImpl(RestTemplate restTemplate, MyMessenger messenger) {
        this.restTemplate = restTemplate;
        this.messenger = messenger;
    }

    @Override
    public void sendSenderAction(String senderId) {
        logger.info("MARKMARKMARK");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        SenderActionDTO senderActionDTO = new SenderActionDTO(
                new Recipient(senderId),
                SenderActionType.typing_on.name());
        HttpEntity<SenderActionDTO> senderActionDTOHttpEntity
                = new HttpEntity<>(senderActionDTO, headers);
        restTemplate.exchange(messenger.getMessagesRequestURI(),
                HttpMethod.POST, senderActionDTOHttpEntity, Void.class);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        senderActionDTOHttpEntity.getBody().setSender_action(SenderActionType.typing_off.name());
        restTemplate.exchange(messenger.getMessagesRequestURI(),
                HttpMethod.POST, senderActionDTOHttpEntity, Void.class);
    }
}
