package com.example.demolibrary.facebook.dto.receive.library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private int id;
    private String title;
    private List<Author> authors;
    private String media_type;
    private Formats formats;
    private int download_count;
}
