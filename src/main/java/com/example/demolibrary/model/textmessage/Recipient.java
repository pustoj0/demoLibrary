package com.example.demolibrary.model.textmessage;

import java.io.Serializable;

public class Recipient implements Serializable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
