package com.example.demolibrary.model.receivedmessage;

public class Message{
    public QuickReply quick_reply;
    public String mid;
    public String text;

    public QuickReply getQuick_reply() {
        return quick_reply;
    }

    public void setQuick_reply(QuickReply quick_reply) {
        this.quick_reply = quick_reply;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "quick_reply=" + quick_reply +
                ", mid='" + mid + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
