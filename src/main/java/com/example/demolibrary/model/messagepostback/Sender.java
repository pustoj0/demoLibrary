package com.example.demolibrary.model.messagepostback;

public class Sender {
    public String user_ref;

    public String getUser_ref() {
        return user_ref;
    }

    public void setUser_ref(String user_ref) {
        this.user_ref = user_ref;
    }

    @Override
    public String toString() {
        return "Sender{" +
                "user_ref='" + user_ref + '\'' +
                '}';
    }
}
