package com.webApp.termProj.service;

import com.webApp.termProj.model.BookEntity;
import com.webApp.termProj.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookEntity> create(final BookEntity bookEntity) {
        validate(bookEntity);

        bookRepository.save(bookEntity);

        return bookRepository.findAll();
    }

    public List<BookEntity> search(final BookEntity bookEntity) {
        return bookRepository.findByTitle(bookEntity.getTitle());
    }

    public List<BookEntity> update(final BookEntity bookEntity) {
        validate(bookEntity);

        final Optional<BookEntity> original = bookRepository.findById(bookEntity.getId());

        original.ifPresent(book -> {
            book.setTitle(bookEntity.getTitle());

            bookRepository.save(book);
        });

        return search(bookEntity);
    }

    public List<BookEntity> delete(final BookEntity bookEntity) {

        validate(bookRepository.findById(bookEntity.getId()));

        try {
            bookRepository.delete(bookEntity);
        } catch (Exception e) {
            log.error("error deleting entity ", bookEntity.getId(), e);

            throw new RuntimeException("error deleting entity " + bookEntity.getId());
        }
        return bookRepository.findAll();
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

    private void validate(final Optional<BookEntity> entity) {
        if (entity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if (entity.get().getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

}
