package com.example.demolibrary.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipientId;
    @OneToOne
    @MapsId
    private Catalog catalog;
}
