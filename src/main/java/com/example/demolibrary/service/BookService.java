package com.example.demolibrary.service;

public interface BookService {
    void addBookToCatalog(Long bookId, String recipientId);

    void deleteBookFromCatalog(Long bookId, String recipientId);
}
