package com.example.library.management.system.service;

import com.example.library.management.system.dtos.BookDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
   public  ResponseEntity<ResponseDtos<Void>> addNewBook(BookDtos bookDetails);

    public ResponseEntity<ResponseDtos<List<BookDtos>>>  getAllBooksWithPagination();

    public  ResponseEntity<ResponseDtos<BookDtos>> getOneBookWithId(Integer id);

    public ResponseEntity<ResponseDtos<Void>> updateBooksDetailsWithId(Integer id, BookDtos updatedBookDetails);

    public ResponseEntity<ResponseDtos<Void>> deleteBookFromRecord(Integer id);
}
