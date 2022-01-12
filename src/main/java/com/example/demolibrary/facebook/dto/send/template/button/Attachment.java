package com.example.demolibrary.facebook.dto.send.template.button;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    public String type;
    public Payload payload;
}
