package com.example.demolibrary.facebook.dto.receive.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    private String id;
    private long time;
    private List<Messaging> messaging;
}
