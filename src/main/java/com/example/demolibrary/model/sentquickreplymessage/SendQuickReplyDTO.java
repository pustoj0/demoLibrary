package com.example.demolibrary.model.sentquickreplymessage;

public class SendQuickReplyDTO {
    public Recipient recipient;
    public String messaging_type;
    public Message message;

    public SendQuickReplyDTO(Recipient recipient, String messaging_type, Message message) {
        this.recipient = recipient;
        this.messaging_type = messaging_type;
        this.message = message;
    }

    public Recipient getRecipient() {
        return recipient;
    }

    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }

    public String getMessaging_type() {
        return messaging_type;
    }

    public void setMessaging_type(String messaging_type) {
        this.messaging_type = messaging_type;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SendQuickReplyDTO{" +
                "recipient=" + recipient +
                ", messaging_type='" + messaging_type + '\'' +
                ", message=" + message +
                '}';
    }
}

