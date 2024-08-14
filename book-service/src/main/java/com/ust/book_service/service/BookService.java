package com.ust.book_service.service;

import com.ust.book_service.dto.Author;
import com.ust.book_service.dto.BookDTO;

import com.ust.book_service.domain.Book;
import com.ust.book_service.feign_clients.AuthorServiceClient;
import com.ust.book_service.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorServiceClient authorServiceClient;

    public BookService(BookRepository bookRepository, AuthorServiceClient authorServiceClient) {
        this.bookRepository = bookRepository;
        this.authorServiceClient = authorServiceClient;
    }

    // Method to get all books along with author information
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Method to get a specific book by ID along with author information
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id);

        return convertToDTO(book);
    }

    // Helper method to convert Book entity to BookDTO
    private BookDTO convertToDTO(Book book) {
        // Fetch the author information using the Feign client
        Author author = authorServiceClient.getAuthorById(book.getAuthorId());
        return new BookDTO(book.getId(), book.getTitle(), author);
    }
}
