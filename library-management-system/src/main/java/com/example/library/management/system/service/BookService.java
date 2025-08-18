package com.example.library.management.system.service;

import com.example.library.management.system.dtos.BookDtos;

import java.util.List;

public interface BookService {
   public String addNewBook(BookDtos bookDetails);

    public List<BookDtos> getAllBooksWithPagination();

    public  BookDtos getOneBookWithId(Integer id);

    public String updateBooksDetailsWithId(Integer id, BookDtos updatedBookDetails);

    public String deleteBookFromRecord(Integer id);
}
