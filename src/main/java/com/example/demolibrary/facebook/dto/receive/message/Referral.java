package com.example.demolibrary.facebook.dto.receive.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Referral {
    private String ref;
    private String source;
    private String type;
}
