package com.example.demolibrary.model.sentmessage;

public class SendMessageDTO {
    private String messaging_type;
    private Recipient recipient;
    private Message message;
    private String sender_action;

    public SendMessageDTO(String messaging_type, Recipient recipient, Message message) {
        this.messaging_type = messaging_type;
        this.recipient = recipient;
        this.message = message;
    }

    public String getMessaging_type() {
        return messaging_type;
    }

    public void setMessaging_type(String messaging_type) {
        this.messaging_type = messaging_type;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String getSender_action() {
        return sender_action;
    }

    public void setSender_action(String sender_action) {
        this.sender_action = sender_action;
    }
}

