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
public class BookRequestDTO {
    String title;

    public BookRequestDTO(final BookEntity bookEntity) {
        this.title = bookEntity.getTitle();
    }

    public static BookEntity toEntity(final BookRequestDTO bookRequestDTO) {
        return BookEntity.builder()
                .title(bookRequestDTO.getTitle())
                .build();
    }
}
