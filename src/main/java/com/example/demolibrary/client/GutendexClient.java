package com.example.demolibrary.client;

import com.example.demolibrary.facebook.dto.receive.library.LibraryDTO;
import com.example.demolibrary.facebook.dto.receive.library.Result;
import com.example.demolibrary.model.Book;
import com.example.demolibrary.repository.BookRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GutendexClient {
    private RestTemplate restTemplate;
    private BookRepository bookRepository;
    private LibraryDTO libraryDTO;

    private static final String apiUrl = "https://gutendex.com/books/";

    public GutendexClient(BookRepository bookRepository, RestTemplate restTemplate) {
        this.bookRepository = bookRepository;
        this.restTemplate = restTemplate;
        this.libraryDTO = restTemplate.getForObject(apiUrl, LibraryDTO.class);
    }

    public LibraryDTO getLibraryDto(){
        return this.libraryDTO;
    }

    public Book getBookById(Long bookId) {
        Result bookDTO = restTemplate.getForObject(apiUrl + bookId, Result.class);
        assert bookDTO != null;
        return resultToBookMapper(bookDTO);
    }

    public Set<Book> searchBooksByTopic(String topic) {
        LibraryDTO libraryDTO
                = restTemplate.getForObject(apiUrl + "?topic=" + topic, LibraryDTO.class);
        assert libraryDTO != null;
        return libraryDTO
                .getResults()
                .stream()
                .map(result -> {
                    Book book = resultToBookMapper(result);
                    return book;
                })
                .collect(Collectors.toSet());
    }

    public void updateBookTable() {
        this.libraryDTO.getResults()
                .forEach(result -> {
                    Book book = resultToBookMapper(result);
                    bookRepository.save(book);
                });
    }

    private Book resultToBookMapper(Result result) {
        Book book = new Book();
        book.setId((long) result.getId());
        book.setTitle(result.getTitle());
        book.setAuthor(result.getAuthors().toString());
        book.setDownloadCount(result.getDownload_count());
        book.setJpeg_url(result.getFormats().getImageJpeg());
        return book;
    }
}
