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
    String title;
    String publisher;
    String userId;

    public BookDTO(final BookEntity bookEntity) {
        this.id = bookEntity.getId();
        this.title = bookEntity.getTitle();
        this.publisher = bookEntity.getPublisher();
        this.userId = bookEntity.getUserId();
    }

    public static BookEntity toEntity(final BookDTO bookDTO) {
        return BookEntity.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .publisher(bookDTO.getPublisher())
                .userId(bookDTO.getUserId())
                .build();
    }

}
