package com.webApp.termProj.dto;

import com.webApp.termProj.model.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookDTO {
    String id;
    String userId;
    String title;
    String author;
    String publisher;

    public BookDTO(final BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.userId = bookEntity.getUserId();
        this.title = bookEntity.getTitle();
        this.author = bookEntity.getAuthor();
        this.publisher = bookEntity.getPublisher();
    }

    public static BookEntity toEntity(final BookDTO bookDTO) {
        return BookEntity.builder()
                .id(bookDTO.getId())
                .userId(bookDTO.getUserId())
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .publisher(bookDTO.getPublisher())
                .build();
    }

}
