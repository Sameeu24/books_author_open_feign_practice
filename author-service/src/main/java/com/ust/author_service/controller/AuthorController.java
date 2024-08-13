package com.ust.author_service.controller;

import com.ust.author_service.domain.Author;
import com.ust.author_service.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/authorservice/")
public class AuthorController {


    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ResponseEntity<List<Author>> getAuthors(){
        var responseBody=authorService.getAllAuthors();
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable long id){
        var responseBody = authorService.getAuthorById(id);
        return ResponseEntity.ok(responseBody);
    }
}
