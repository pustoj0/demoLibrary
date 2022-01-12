package com.example.demolibrary.facebook.dto.receive.library;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Formats {
    @JsonProperty("image/jpeg")
    private String imageJpeg;
}
