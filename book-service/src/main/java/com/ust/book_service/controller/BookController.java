package com.ust.book_service.controller;


import com.ust.book_service.domain.Book;
import com.ust.book_service.dto.Author;
import com.ust.book_service.dto.BookDto;
import com.ust.book_service.feign_clients.AuthorServiceClient;
import com.ust.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/bookservice/")
public class BookController {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final AuthorServiceClient authorClient;

    public BookController(BookRepository bookRepository, AuthorServiceClient authorClient) {
        this.bookRepository = bookRepository;
        this.authorClient = authorClient;
    }

    @GetMapping("/book")
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(book -> {
            Author author = authorClient.getAuthorById(book.getAuthorId());
            return new BookDto(book.getId(), book.getTitle(), author);
        }).collect(Collectors.toList());
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null; // Or throw a custom exception
        }
        Author author = authorClient.getAuthorById(book.getAuthorId());
        return new BookDto(book.getId(), book.getTitle(), author);
    }
}
