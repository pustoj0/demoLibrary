package com.example.demolibrary.facebook.dto.send.textmessage;

import com.example.demolibrary.facebook.dto.DTO;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TextMessageDTO implements DTO {
    private Recipient recipient;
    private String messaging_type;
    private Message message;
}
