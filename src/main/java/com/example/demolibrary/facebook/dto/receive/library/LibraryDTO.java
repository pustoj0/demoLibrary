package com.example.demolibrary.facebook.dto.receive.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryDTO{
    private int count;
    private String next;
    private String previous;
    private List<Result> results;
}
