package com.webApp.termProj.service;

import com.webApp.termProj.model.BookEntity;
import com.webApp.termProj.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> create(final BookEntity bookEntity) {
        validate(bookEntity);

        bookRepository.save(bookEntity);

        return bookRepository.findByUserId(bookEntity.getUserId());
    }

    public List<BookEntity> search(final BookEntity bookEntity) {
        return bookRepository.findByTitle(bookEntity.getTitle());
    }

    private void validate(final BookEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

}
