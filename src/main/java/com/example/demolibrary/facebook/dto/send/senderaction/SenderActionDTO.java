package com.example.demolibrary.facebook.dto.send.senderaction;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SenderActionDTO{
    private Recipient recipient;
    private String sender_action;
}
