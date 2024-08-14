package com.ust.book_service.controller;


import com.ust.book_service.domain.Book;
import com.ust.book_service.dto.Author;
import com.ust.book_service.dto.BookDTO;
import com.ust.book_service.feign_clients.AuthorServiceClient;
import com.ust.book_service.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorServiceClient authorClient;

    @GetMapping("/")
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(book -> {
                    Author author = authorClient.getAuthorById(book.getAuthorId());
                    return new BookDTO(book.getId(), book.getTitle(), author);
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null; // Handle this case appropriately in production code
        }
        Author author = authorClient.getAuthorById(book.getAuthorId());
        return new BookDTO(book.getId(), book.getTitle(), author);
    }
}



