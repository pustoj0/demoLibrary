package com.example.demolibrary.facebook.dto.receive.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postback {
    public String mid;
    public String title;
    public String payload;
    public Referral referral;
}
