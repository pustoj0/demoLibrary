package com.example.demolibrary.model.template.generictemplate;

public class Attachment {
    public String type;
    public Payload payload;

    public Attachment(String type, Payload payload) {
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
