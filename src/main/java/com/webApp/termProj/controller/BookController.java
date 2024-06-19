package com.webApp.termProj.controller;

import com.webApp.termProj.dto.BookDTO;
import com.webApp.termProj.dto.ResponseDTO;
import com.webApp.termProj.model.BookEntity;
import com.webApp.termProj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    // 좋아요가 눌린 게시물 조회
    @GetMapping("/liked")
    public ResponseEntity<?> getLikedBooks() {
        List<BookEntity> entities = bookService.getLikedBooks();
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }

    // 좋아요 상태 변경
    @PutMapping("/like/{title}")
    public ResponseEntity<?> toggleLike(@PathVariable String title) {
        try {
            BookEntity book = bookService.search(title);
            bookService.updateLikeStatus(book);
            BookDTO dto = new BookDTO(book);
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(Collections.singletonList(dto)).build();
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            String error = e.getMessage();
            ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }


    // 제품 정보 추가
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody BookDTO bookDTO) {
        try {
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

    // 제품 정보 검색
    @GetMapping
    public ResponseEntity<?> searchBook(@RequestParam String title) {
        // 검색 결과를 BookEntity 객체로 가져옴
        BookEntity entity = bookService.search(title);

        // BookEntity 객체를 BookDTO로 변환
        BookDTO dto = new BookDTO(entity);

        // ResponseDTO를 생성하여 응답을 반환
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder()
                .data(Collections.singletonList(dto))
                .build();
        return ResponseEntity.ok().body(response);
    }


    // 제품 정보 수정
    @PutMapping
    public ResponseEntity<?> updateBook(@RequestBody BookDTO bookDto) {

        BookEntity entity = BookDTO.toEntity(bookDto);

        List<BookEntity> entities = bookService.update(entity);
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);

    }

    // 제품 정보 삭제
    @DeleteMapping
    public ResponseEntity<?> deleteBook(@RequestBody BookDTO bookDTO) {
        try {
            BookEntity entity = BookDTO.toEntity(bookDTO);
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
    
    // 제품 전체 조회
    @GetMapping("/all")
    public ResponseEntity<?> getAllBook() {

        List<BookEntity> entities = bookService.getAll();
        List<BookDTO> dtos = entities.stream().map(BookDTO::new).collect(Collectors.toList());
        ResponseDTO<BookDTO> response = ResponseDTO.<BookDTO>builder().data(dtos).build();
        return ResponseEntity.ok().body(response);
    }
}
