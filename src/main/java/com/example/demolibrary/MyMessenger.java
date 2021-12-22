package com.example.demolibrary;

import com.example.demolibrary.exception.MessengerVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public final class MyMessenger {
    public static final String MODE_REQUEST_PARAM_NAME = "hub.mode";
    public static final String CHALLENGE_REQUEST_PARAM_NAME = "hub.challenge";
    public static final String VERIFY_TOKEN_REQUEST_PARAM_NAME = "hub.verify_token";
    public static final String SIGNATURE_HEADER_NAME = "X-Hub-Signature";
    
    private final String pageAccessToken;
    private final String appSecret;
    private final String verifyToken;
    private final String messagesRequestURI;


    public MyMessenger(String pageAccessToken, String appSecret, String verifyToken) {
        this.pageAccessToken = pageAccessToken;
        this.appSecret = appSecret;
        this.verifyToken = verifyToken;
        this.messagesRequestURI = String.format("https://graph.facebook.com/v12.0/me/messages?access_token=%s",
                pageAccessToken);
    }

    public static MyMessenger create(String pageAccessToken, String appSecret, String verifyToken) {
        if (pageAccessToken == null) {
            throw new IllegalArgumentException("pageAccessToken is null");
        } else if (appSecret == null) {
            throw new IllegalArgumentException("appSecret is null");
        } else if (verifyToken == null) {
            throw new IllegalArgumentException("verifyToken is null");
        } else {
            return new MyMessenger(pageAccessToken, appSecret, verifyToken);
        }
    }
    public void verifyWebhook(String mode, String verifyToken) throws MessengerVerificationException {
        if (mode == null) {
            throw new IllegalArgumentException("mode is null");
        } else if (verifyToken == null) {
            throw new IllegalArgumentException("verifyToken is null");
        } else if (!mode.equals("subscribe")) {
            throw new MessengerVerificationException("Webhook verification failed. Mode '" + mode + "' is invalid.");
        } else if (!verifyToken.equals(this.verifyToken)) {
            throw new MessengerVerificationException("Webhook verification failed. Verification token '" + verifyToken + "' is invalid.");
        }
    }

    public ResponseEntity<String> send(String recipientId, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("messaging_type", "RESPONSE");
        ObjectNode recipientNode = objectMapper.createObjectNode();
        recipientNode.put("id", recipientId);
        rootNode.put("recipient", recipientNode);
        ObjectNode messageNode = objectMapper.createObjectNode();
        messageNode.put("text", message);
        rootNode.put("message", messageNode);
        System.out.println(rootNode.toPrettyString());
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(headers)
                .location(URI.create(messagesRequestURI))
                .body(rootNode.asText());
    }
}
