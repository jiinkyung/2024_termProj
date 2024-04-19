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
public class BookTitleDTO {
    String title;

    public BookTitleDTO(final BookEntity bookEntity) {
        this.title = bookEntity.getTitle();
    }

    public static BookEntity toEntity(final BookTitleDTO bookRequestDTO) {
        return BookEntity.builder()
                .title(bookRequestDTO.getTitle())
                .build();
    }
}
