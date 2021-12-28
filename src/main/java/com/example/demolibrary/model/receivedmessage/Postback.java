package com.example.demolibrary.model.receivedmessage;

public class Postback {
    public String mid;
    public String title;
    public String payload;
    public Referral referral;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
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

    public Referral getReferral() {
        return referral;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }

    @Override
    public String toString() {
        return "Postback{" +
                "mid='" + mid + '\'' +
                ", title='" + title + '\'' +
                ", payload='" + payload + '\'' +
                ", referral=" + referral +
                '}';
    }
}
