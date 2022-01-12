package com.example.demolibrary.facebook.dto.receive.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messaging {
    private Sender sender;
    private Recipient recipient;
    private long timestamp;
    private Message message;
    private Postback postback;
}

