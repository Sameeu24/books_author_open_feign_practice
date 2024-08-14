package com.ust.book_service.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    private Long id;
    private String title;
    private Long authorId;
    public Book(Long id,String title,Long authorId){
        this.id=id;
        this.title=title;
        this.authorId=authorId;
    }
    public void toDto(){

    }

}