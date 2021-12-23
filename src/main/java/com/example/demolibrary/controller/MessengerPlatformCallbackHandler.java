package com.example.demolibrary.controller;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.exception.MessengerVerificationException;
import com.example.demolibrary.model.textmessage.Message;
import com.example.demolibrary.model.textmessage.Recipient;
import com.example.demolibrary.model.textmessage.SendMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demolibrary.model.receivedmessage.*;
import org.springframework.web.client.RestTemplate;

import static com.example.demolibrary.MyMessenger.*;

@RestController
@RequestMapping("/callback")
public class MessengerPlatformCallbackHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessengerPlatformCallbackHandler.class);
    private final MyMessenger myMessenger;

    public MessengerPlatformCallbackHandler(MyMessenger myMessenger) {
        this.myMessenger = myMessenger;
    }

    @GetMapping
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

    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody MessagePayloadDTO messagePayloadDTO,
                                                 @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        logger.info("Received Messenger Platform callback - payload: {} | signature: {}", messagePayloadDTO, signature);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode messagePayload = mapper.readTree(mapper.writeValueAsString(messagePayloadDTO));

            String senderId = messagePayload.findPath("sender").findPath("id").asText();
            String messageText = messagePayload.findPath("text").asText();

            Message message = new Message();
            message.setText(messageText);
            Recipient recipient = new Recipient();
            recipient.setId(senderId);

            SendMessageDTO sendMessageDTO = new SendMessageDTO();
            sendMessageDTO.setMessaging_type("RESPONSE");
            sendMessageDTO.setMessage(message);
            sendMessageDTO.setRecipient(recipient);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<SendMessageDTO> entity = new HttpEntity<>(sendMessageDTO, headers);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(myMessenger.getMessagesRequestURI(), HttpMethod.POST, entity, Void.class);
            return ResponseEntity.ok("Good");
        } catch (JsonProcessingException e) {
            logger.warn("Processing of callback payload failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
