package com.ust.book_service.domain;


import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    private Long id;
    private String title;
    private Long authorId;
    // Getters and setters
}
