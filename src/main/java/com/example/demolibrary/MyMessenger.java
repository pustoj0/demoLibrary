package com.example.demolibrary;

import com.example.demolibrary.exception.MessengerVerificationException;

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

    public String getPageAccessToken() {
        return pageAccessToken;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getVerifyToken() {
        return verifyToken;
    }

    public String getMessagesRequestURI() {
        return messagesRequestURI;
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
}
