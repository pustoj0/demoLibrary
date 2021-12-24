package com.example.demolibrary.model.messagepostback;

public class MessagePostbackDTO{
    public Sender sender;
    public Recipient recipient;
    public long timestamp;
    public Postback postback;

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Postback getPostback() {
        return postback;
    }

    public void setPostback(Postback postback) {
        this.postback = postback;
    }

    @Override
    public String toString() {
        return "MessagePostbackDTO{" +
                "sender=" + sender +
                ", recipient=" + recipient +
                ", timestamp=" + timestamp +
                ", postback=" + postback +
                '}';
    }
}
