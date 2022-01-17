package com.example.demolibrary.facebook.dto.send.template.generic;

import com.example.demolibrary.facebook.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericTemplateDTO implements DTO {
    private Recipient recipient;
    private Message message;
}

