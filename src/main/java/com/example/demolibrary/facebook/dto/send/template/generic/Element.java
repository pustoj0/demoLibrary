package com.example.demolibrary.facebook.dto.send.template.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Element {
    private String title;
    private String image_url;
    private String subtitle;
    private DefaultAction default_action;
    private List<Button> buttons;
}
