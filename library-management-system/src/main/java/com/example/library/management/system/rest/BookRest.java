package com.example.library.management.system.rest;

import com.example.library.management.system.dtos.BookDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
public interface BookRest {

    @PostMapping("/books")
    public ResponseEntity<ResponseDtos<Void>> addNewBook(@RequestBody BookDtos requestBody);

    @GetMapping("/books")
    public ResponseEntity<ResponseDtos<List<BookDtos>>> getAllBooksWithPagination();

    @GetMapping("/books/{id}")
    public ResponseEntity<ResponseDtos<BookDtos>> getOneBookWithId(@RequestParam Integer id);

    @PutMapping("/books/{id}")
    public ResponseEntity<ResponseDtos<Void>> updateBooksDetailsWithId(@RequestParam Integer id,@RequestBody BookDtos updatedBookDetails);

    @DeleteMapping("/books/{id}")
    public ResponseEntity<ResponseDtos<Void>> deleteBookFromRecord (@RequestParam Integer id);
}
