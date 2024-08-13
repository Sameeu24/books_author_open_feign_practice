package com.ust.book_service.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BookDto{
    private Long id;
    private String title;
    private Author author;
    // Getters and setters

    public BookDto(Long id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}