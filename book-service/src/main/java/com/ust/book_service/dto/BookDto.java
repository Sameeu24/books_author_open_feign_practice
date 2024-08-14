    package com.ust.book_service.dto;

    import com.ust.book_service.domain.Book;
    import com.ust.book_service.feign_clients.AuthorServiceClient;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;




    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class BookDTO {
        private Long id;
        private String title;
        private Author author;

        public static BookDTO convertToDto(Book book, AuthorServiceClient authorClient) {
            Author author = authorClient.getAuthorById(book.getAuthorId());
            return new BookDTO(book.getId(), book.getTitle(), author);
        }

    }
