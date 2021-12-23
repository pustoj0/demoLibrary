package com.example.demolibrary.model.sentquickreplymessage;

public class QuickReply {
    public String content_type;
    public String title;
    public String payload;
    public String image_url;

    public QuickReply(String content_type, String title, String payload, String image_url) {
        this.content_type = content_type;
        this.title = title;
        this.payload = payload;
        this.image_url = image_url;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
