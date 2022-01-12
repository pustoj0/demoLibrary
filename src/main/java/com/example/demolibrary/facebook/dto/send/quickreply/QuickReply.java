package com.example.demolibrary.facebook.dto.send.quickreply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickReply {
    private String content_type;
    private String title;
    private String payload;
    private String image_url;
}
