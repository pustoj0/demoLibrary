package com.example.demolibrary.model.template.generictemplate;

public class Message {
    public Attachment attachment;

    public Message(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
