package com.webApp.termProj.controller;

import com.webApp.termProj.dto.BookDTO;
import com.webApp.termProj.dto.BookRequestDTO;
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
            String temporaryUserId = "JinKyungHan";

            BookEntity entity = BookDTO.toEntity(bookDTO);

            entity.setId(null);
            entity.setUserId(temporaryUserId);

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
    public ResponseEntity<?> searchBook(@RequestBody BookRequestDTO bookRequestDTO) {

        BookEntity entity = BookRequestDTO.toEntity(bookRequestDTO);

        List<BookEntity> entities = bookService.search(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }
}
