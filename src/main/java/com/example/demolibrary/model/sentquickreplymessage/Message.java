package com.example.demolibrary.model.sentquickreplymessage;

import java.util.List;

public class Message {
    public String text;
    public List<QuickReply> quick_replies;

    public Message(String text, List<QuickReply> quick_replies) {
        this.text = text;
        this.quick_replies = quick_replies;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuickReply> getQuick_replies() {
        return quick_replies;
    }

    public void setQuick_replies(List<QuickReply> quick_replies) {
        this.quick_replies = quick_replies;
    }
}
