package com.example.demolibrary.model.senderaction;

public class SenderActionDTO{
    public Recipient recipient;
    public String sender_action;

    public SenderActionDTO(Recipient recipient, String sender_action) {
        this.recipient = recipient;
        this.sender_action = sender_action;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getSender_action() {
        return sender_action;
    }

    public void setSender_action(String sender_action) {
        this.sender_action = sender_action;
    }
}
