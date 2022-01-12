package com.example.demolibrary.facebook.dto.send.template.generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload {
    private String template_type;
    private Set<Element> elements;
}
