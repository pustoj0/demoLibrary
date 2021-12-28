package com.example.demolibrary.model.template.generictemplate;

public class GenericTemplateDTO{
    public Recipient recipient;
    public Message message;

    public GenericTemplateDTO(Recipient recipient, Message message) {
        this.recipient = recipient;
        this.message = message;
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
}

