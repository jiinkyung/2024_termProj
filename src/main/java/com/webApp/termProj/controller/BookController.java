package com.webApp.termProj.controller;

import com.webApp.termProj.dto.BookDTO;
import com.webApp.termProj.dto.BookTitleDTO;
import com.webApp.termProj.dto.ResponseDTO;
import com.webApp.termProj.model.BookEntity;
import com.webApp.termProj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try{
            BookEntity entity = BookDTO.toEntity(bookDTO);

            List<BookEntity> entities = bookService.create(entity);

            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());

            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();

            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<?> searchBook(@RequestBody BookTitleDTO bookRequestDTO) {

        BookEntity entity = BookTitleDTO.toEntity(bookRequestDTO);

        List<BookEntity> entities = bookService.search(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDto) {
        String temporaryUserId = "JinKyungHan";

        BookEntity entity = BookDTO.toEntity(bookDto);
        entity.setUserId(temporaryUserId);

        List<BookEntity> entities = bookService.update(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO) {
        try {
            String temporaryUserId = "JinKyungHan";
            BookEntity entity = BookDTO.toEntity(bookDTO);
            entity.setUserId(temporaryUserId);
            List<BookEntity> entities = bookService.delete(entity);

            List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());

            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
