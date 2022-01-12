package com.example.demolibrary.facebook.dto.send.textmessage;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessageDTO {
    private Recipient recipient;
    private String messaging_type;
    private Message message;
}
