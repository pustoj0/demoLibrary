package com.example.demolibrary.model.receivedmessage;

public class QuickReply {
    public String payload;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "QuickReply{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
