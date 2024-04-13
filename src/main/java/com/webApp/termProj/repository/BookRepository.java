package com.webApp.termProj.repository;

import com.webApp.termProj.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByUserId(String userId);
    List<BookEntity> findByTitle(String title);
}
