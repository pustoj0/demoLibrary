package com.example.demolibrary.facebook.dto.send.quickreply;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String text;
    private List<QuickReply> quick_replies;
}
