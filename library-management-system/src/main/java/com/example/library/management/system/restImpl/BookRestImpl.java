package com.example.library.management.system.restImpl;

import com.example.library.management.system.dtos.BookDtos;
import com.example.library.management.system.rest.BookRest;
import com.example.library.management.system.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestImpl implements BookRest {

    @Autowired
    private BookService bookService;

    @Override
    public String addNewBook(BookDtos bookDetails) {
        try{
            return bookService.addNewBook(bookDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookDtos> getAllBooksWithPagination() {
        try{
            return bookService.getAllBooksWithPagination();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BookDtos getOneBookWithId(Integer id) {
        try{
            return bookService.getOneBookWithId(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String updateBooksDetailsWithId(Integer id, BookDtos updatedBookDetails) {
        try{
            return bookService.updateBooksDetailsWithId(id,updatedBookDetails);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteBookFromRecord(Integer id) {
        try{
            return bookService.deleteBookFromRecord(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
