package com.example.demolibrary.facebook.dto.receive.message;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePayloadDTO {
    private String object;
    private List<Entry> entry;
}