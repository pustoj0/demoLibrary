package com.example.demolibrary.facebook.dto.send.quickreply;

import com.example.demolibrary.facebook.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickReplyDTO implements DTO {
    private Recipient recipient;
    private String messaging_type;
    private Message message;
}

