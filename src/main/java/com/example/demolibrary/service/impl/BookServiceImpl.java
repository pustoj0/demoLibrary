package com.example.demolibrary.service.impl;

import com.example.demolibrary.client.GutendexClient;
import com.example.demolibrary.model.Book;
import com.example.demolibrary.model.Catalog;
import com.example.demolibrary.model.User;
import com.example.demolibrary.repository.BookRepository;
import com.example.demolibrary.repository.CatalogRepository;
import com.example.demolibrary.repository.UserRepository;
import com.example.demolibrary.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {
    private GutendexClient gutendexClient;
    private BookRepository bookRepository;
    private UserRepository userRepository;
    private CatalogRepository catalogRepository;

    @Override
    public void addBookToCatalog(Long bookId, String recipientId) {
        Book book = gutendexClient.getBookById(bookId);
        bookRepository.save(book);
        User user = userRepository.getUserByRecipientId(recipientId).orElseThrow();
        Catalog catalog = user.getCatalog();
        catalog.getBooks().add(book);
        catalogRepository.save(catalog);
    }

    @Override
    public void deleteBookFromCatalog(Long bookId, String recipientId) {
        User user = userRepository.getUserByRecipientId(recipientId).orElseThrow();
        Catalog catalog = user.getCatalog();
        Book book = bookRepository.findById(bookId).orElseThrow();
        catalog.getBooks().remove(book);
        catalogRepository.save(catalog);
    }


}
