package com.example.demolibrary.facebook.dto.send.template.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    private String type;
    private Payload payload;
}
