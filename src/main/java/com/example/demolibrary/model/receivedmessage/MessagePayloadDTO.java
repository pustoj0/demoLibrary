package com.example.demolibrary.model.receivedmessage;

import java.util.List;

public class MessagePayloadDTO {
    public String object;
    public List<Entry> entry;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}

