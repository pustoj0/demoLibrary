package com.example.demolibrary.service.impl;

import com.example.demolibrary.MyMessenger;
import com.example.demolibrary.exception.MessengerVerificationException;
import com.example.demolibrary.service.VerifyWebhookService;
import org.springframework.stereotype.Service;

@Service
public class VerifyWebhookServiceImpl implements VerifyWebhookService {
    private MyMessenger messenger;

    public VerifyWebhookServiceImpl(MyMessenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public void verifyWebhook(String mode, String verifyToken) throws MessengerVerificationException {
        if (mode == null) {
            throw new IllegalArgumentException("mode is null");
        } else if (verifyToken == null) {
            throw new IllegalArgumentException("verifyToken is null");
        } else if (!mode.equals("subscribe")) {
            throw new MessengerVerificationException("Webhook verification failed. Mode '"
                    + mode + "' is invalid.");
        } else if (!verifyToken.equals(messenger.getVerifyToken())) {
            throw new MessengerVerificationException("Webhook verification failed. Verification token '"
                    + verifyToken + "' is invalid.");
        }
    }
}
