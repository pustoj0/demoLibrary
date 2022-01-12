package com.example.demolibrary.facebook.dto.send.template.button;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    public String template_type;
    public String text;
    public List<Button> buttons;
}
