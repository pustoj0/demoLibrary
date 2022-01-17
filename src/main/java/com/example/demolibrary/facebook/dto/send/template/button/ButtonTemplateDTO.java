package com.example.demolibrary.facebook.dto.send.template.button;

import com.example.demolibrary.facebook.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonTemplateDTO implements DTO {
    public Recipient recipient;
    public Message message;
}