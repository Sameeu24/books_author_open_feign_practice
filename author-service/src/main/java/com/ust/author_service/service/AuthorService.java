package com.ust.author_service.service;

import com.ust.author_service.domain.Author;
import com.ust.author_service.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AuthorService {

    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id){
        return authorRepository.findById(id);
    }
}
