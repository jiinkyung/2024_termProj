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

    public void updateLikeStatus(final BookEntity bookEntity) throws Exception {
        BookEntity book = bookRepository.findById(bookEntity.getId()).orElseThrow(() -> new Exception("Book not found"));
        book.setLiked(!book.isLiked());
        bookRepository.save(book);
    }

    public List<BookEntity> getLikedBooks() {
        return bookRepository.findByLiked(true);
    }

    // 전제 제품 정보
    public List<BookEntity> getAll() {
        return bookRepository.findAll();
    }

    // 제품 정보 추가
    public List<BookEntity> create(final BookEntity bookEntity) {
        validate(bookEntity);

        bookRepository.save(bookEntity);

        return bookRepository.findAll();
    }

    // 제품 정보 검색
    public BookEntity search(final String title) {
        return bookRepository.findByTitle(title);
    }

    // 제품 정보 수정
    public List<BookEntity> update(final BookEntity bookEntity) {
        validate(bookEntity);

        final Optional<BookEntity> original = bookRepository.findById(bookEntity.getId());

        original.ifPresent(book -> {
            book.setTitle(bookEntity.getTitle());
            book.setAuthor(bookEntity.getAuthor());
            book.setPublisher(bookEntity.getPublisher());
            book.setUserId(bookEntity.getUserId());
            bookRepository.save(book);
        });

        return getAll();
    }

    // 제품 정보 삭제
    public List<BookEntity> delete(final BookEntity bookEntity) {

        BookEntity entities = bookRepository.findByTitle(bookEntity.getTitle());

        try {
            bookRepository.deleteById(entities.getId());
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
}
