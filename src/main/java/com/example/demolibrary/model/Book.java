package com.example.demolibrary.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String jpeg_url;
    private int downloadCount;
}
