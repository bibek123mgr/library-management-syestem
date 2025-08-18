package com.example.library.management.system.rest;

import com.example.library.management.system.dtos.BookDtos;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
public interface BookRest {

    @PostMapping("/books")
    public String addNewBook(@RequestBody BookDtos requestBody);

    @GetMapping("/books")
    public List<BookDtos> getAllBooksWithPagination();

    @GetMapping("/books/{id}")
    public BookDtos getOneBookWithId(@RequestParam Integer id);

    @PutMapping("/books/{id}")
    public String updateBooksDetailsWithId(@RequestParam Integer id,@RequestBody BookDtos updatedBookDetails);

    @DeleteMapping("/books/{id}")
    public String deleteBookFromRecord (@RequestParam Integer id);
}
