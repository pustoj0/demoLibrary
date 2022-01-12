package com.example.demolibrary.facebook.dto.send.template.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Button {
    private String type;
    private String url;
    private String title;
    private String payload;
}
