package com.example.demolibrary.facebook.service;

import com.example.demolibrary.exception.MessengerVerificationException;

public interface VerifyWebhookService {
    void verifyWebhook(String mode, String verifyToken) throws MessengerVerificationException;
}
