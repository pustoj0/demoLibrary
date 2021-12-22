package com.example.demolibrary.controller;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.exception.MessengerVerificationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.demolibrary.MyMessenger.*;

@RestController
@RequestMapping("/callback")
public class MessengerPlatformCallbackHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessengerPlatformCallbackHandler.class);
    private final MyMessenger myMessenger;

    public MessengerPlatformCallbackHandler(MyMessenger myMessenger) {
        this.myMessenger = myMessenger;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
        logger.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode, verifyToken, challenge);
        try {
            this.myMessenger.verifyWebhook(mode, verifyToken);
            return ResponseEntity.ok(challenge);
        } catch (MessengerVerificationException e) {
            logger.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> handleCallback(@RequestBody final String payload,
                                               @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        logger.debug("Received Messenger Platform callback - payload: {} | signature: {}", payload, signature);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(payload);
            JsonNode node = root.path("text");
            System.out.println(node.toString());
            System.out.println(root.toPrettyString());
            return ResponseEntity.ok("Everythingn is ok");
        } catch (JsonProcessingException e) {
            logger.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
