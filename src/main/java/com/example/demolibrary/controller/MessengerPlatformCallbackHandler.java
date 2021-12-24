package com.example.demolibrary.controller;

import com.example.demolibrary.exception.MessengerVerificationException;
import com.example.demolibrary.model.messagepostback.MessagePostbackDTO;
import com.example.demolibrary.service.SendMessageService;
import com.example.demolibrary.service.VerifyWebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.example.demolibrary.model.receivedmessage.*;
import static com.example.demolibrary.MyMessenger.*;

@RestController
@RequestMapping("/callback")
public class MessengerPlatformCallbackHandler {
    private static final Logger logger = LoggerFactory.getLogger(MessengerPlatformCallbackHandler.class);
    private SendMessageService sendTextMessageService;
    private SendMessageService sendQuickReplyMessageService;
    private VerifyWebhookService verifyWebhookService;

    public MessengerPlatformCallbackHandler(@Qualifier("sendTextMessageServiceImpl")
                                                    SendMessageService sendTextMessageService,
                                            @Qualifier("sendQuickReplyMessageServiceImpl")
                                                    SendMessageService sendQuickReplyMessageService,
                                            VerifyWebhookService verifyWebhookService) {
        this.sendTextMessageService = sendTextMessageService;
        this.sendQuickReplyMessageService = sendQuickReplyMessageService;
        this.verifyWebhookService = verifyWebhookService;
    }

    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
        logger.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode, verifyToken, challenge);
        try {
            verifyWebhookService.verifyWebhook(mode, verifyToken);
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
        sendQuickReplyMessageService.sendMessage(messagePayloadDTO);
        return ResponseEntity.ok("Message was sent");
    }

    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody MessagePostbackDTO messagePayloadDTO,
                                                 @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        logger.info("Received Messenger Platform callback - payload: {} | signature: {}", messagePayloadDTO, signature);
        System.out.println(messagePayloadDTO);
        return ResponseEntity.ok("Message was sent");
    }
}
