package com.example.demolibrary.model.messagepostback;

public class Sender {
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "id='" + id + '\'' +
                '}';
    }
}
