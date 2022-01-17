package com.example.demolibrary.facebook.flow.welcome;

import com.example.demolibrary.facebook.dto.send.textmessage.TextMessageUtil;
import com.example.demolibrary.facebook.flow.Flow;
import com.example.demolibrary.facebook.dto.send.template.button.*;
import com.example.demolibrary.facebook.dto.send.template.button.Message;
import com.example.demolibrary.facebook.dto.send.template.button.Recipient;
import com.example.demolibrary.facebook.service.SendMessageService;
import com.example.demolibrary.model.Catalog;
import com.example.demolibrary.model.User;
import com.example.demolibrary.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Log4j2
public class WelcomeFlow implements Flow {
    private SendMessageService messageService;
    private UserService userService;

    private final String welcomeMessage = "Hi! I'm Libryx, chatbot, created to help you" +
            " find books you are looking for and add them in your catalog.";

    @Override
    public void run(String recipientId) {
        Optional<User> userOptional = userService.getUserByRecipientId(recipientId);
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setRecipientId(recipientId);
            Catalog catalog = new Catalog();
            user.setCatalog(catalog);
            userService.add(user);
        }
        messageService.sendMessage(
                TextMessageUtil.createTextMessage(recipientId, welcomeMessage));
        ButtonTemplateDTO buttonDTO = createRecommendationButton(recipientId);
        messageService.sendMessage(buttonDTO);
    }

    private ButtonTemplateDTO createRecommendationButton(String recipientId) {
        Recipient recipient = new Recipient(recipientId);
        Attachment attachment = new Attachment();
        attachment.setType("template");
        Payload payload = new Payload();
        payload.setTemplate_type("button");
        payload.setText("Do you wish to see our recommendations?");
        Button button = new Button();
        button.setTitle("Show me");
        button.setType("postback");
        button.setPayload("recommendations");
        payload.setButtons(List.of(button));
        attachment.setPayload(payload);
        Message buttonMessage = new Message(attachment);
        return new ButtonTemplateDTO(recipient, buttonMessage);
    }
}
