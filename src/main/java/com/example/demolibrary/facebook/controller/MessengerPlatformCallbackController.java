package com.example.demolibrary.facebook.controller;

import com.example.demolibrary.exception.MessengerVerificationException;
import com.example.demolibrary.facebook.dto.receive.message.MessagePayloadDTO;
import com.example.demolibrary.facebook.service.HandleMessagePayloadService;
import com.example.demolibrary.facebook.service.VerifyWebhookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import static com.example.demolibrary.facebook.Messenger.*;

@RestController
@RequestMapping("/callback")
@Log4j2
@AllArgsConstructor
public class MessengerPlatformCallbackController {
    private VerifyWebhookService verifyWebhookService;
    private HandleMessagePayloadService handleMessagePayload;

    @GetMapping
    public ResponseEntity<String> verifyWebhook(@RequestParam(MODE_REQUEST_PARAM_NAME) final String mode,
                                                @RequestParam(VERIFY_TOKEN_REQUEST_PARAM_NAME) final String verifyToken,
                                                @RequestParam(CHALLENGE_REQUEST_PARAM_NAME) final String challenge) {
        log.debug("Received Webhook verification request - mode: {} | verifyToken: {} | challenge: {}", mode, verifyToken, challenge);
        try {
            verifyWebhookService.verifyWebhook(mode, verifyToken);
            return ResponseEntity.ok(challenge);
        } catch (MessengerVerificationException e) {
            log.warn("Webhook verification failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> handleCallback(@RequestBody MessagePayloadDTO messagePayloadDTO,
                                                 @RequestHeader(SIGNATURE_HEADER_NAME) final String signature) {
        log.info("Received Messenger Platform callback - payload: {} | signature: {}", messagePayloadDTO, signature);
        handleMessagePayload.handle(messagePayloadDTO);
        return ResponseEntity.ok("Message was sent");
    }
}
