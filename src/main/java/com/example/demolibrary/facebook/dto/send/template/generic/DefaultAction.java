package com.example.demolibrary.facebook.dto.send.template.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DefaultAction {
    private String type;
    private String url;
    private String webview_height_ratio;
}
