package com.example.demolibrary.facebook.dto.send.template.button;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Button {
    public String type;
    public String url;
    public String title;
    public String payload;
}
