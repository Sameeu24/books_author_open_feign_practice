package com.ust.book_service.feign_clients;


import com.ust.book_service.dto.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "author-service",url = "http://localhost:8100/api/v1/authorservice/")
public interface AuthorServiceClient {

    @GetMapping("/author/{id}")
    public Author getAuthorById(@PathVariable long id);


}
