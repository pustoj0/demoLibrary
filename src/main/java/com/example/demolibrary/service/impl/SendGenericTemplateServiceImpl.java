package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.model.receivedmessage.MessagePayloadDTO;
import com.example.demolibrary.model.template.generictemplate.*;
import com.example.demolibrary.service.SendMessageService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendGenericTemplateServiceImpl implements SendMessageService {
    private RestTemplate restTemplate;
    private MyMessenger messenger;

    public SendGenericTemplateServiceImpl(RestTemplate restTemplate, MyMessenger messenger) {
        this.restTemplate = restTemplate;
        this.messenger = messenger;
    }


    @Override
    public void sendMessage(MessagePayloadDTO messagePayloadDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        messagePayloadDTO
                .getEntry()
                .forEach(entry -> {
                    entry.getMessaging()
                            .forEach(messaging -> {
                                String senderId = messaging.getSender().getId();
                                Recipient recipient = new Recipient(senderId);
                                DefaultAction defaultAction = new DefaultAction("web_url", "https://www.originalcoastclothing.com/", "tall");
                                Button button1 = new Button("web_url", "https://www.originalcoastclothing.com/", "View Website", "");
                                Button button2 = new Button("postback", "", "Start Chatting", "payload");
                                List<Button> buttons = new ArrayList<>();
                                buttons.add(button1);
                                buttons.add(button2);
                                Element element = new Element("Welcome!",
                                        "https://raw.githubusercontent.com/fbsamples/original-coast-clothing/main/public/styles/male-work.jpg",
                                        "We have the right hat for everyone.",
                                        defaultAction, buttons);
                                List<Element> elements = new ArrayList<>();
                                elements.add(element);
                                Payload payload = new Payload("generic", elements);
                                Attachment attachment = new Attachment("template", payload);
                                Message message = new Message(attachment);
                                GenericTemplateDTO genericTemplate = new GenericTemplateDTO(recipient, message);
                                HttpEntity<GenericTemplateDTO> entity = new HttpEntity<>(genericTemplate, headers);
                                restTemplate.exchange(messenger.getMessagesRequestURI(),
                                        HttpMethod.POST, entity, Void.class);
                            });
                });
    }
}

