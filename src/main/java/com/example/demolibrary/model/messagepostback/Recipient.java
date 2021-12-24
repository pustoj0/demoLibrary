package com.example.demolibrary.model.messagepostback;

public class Recipient {
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Recipient{" +
                "id='" + id + '\'' +
                '}';
    }
}
