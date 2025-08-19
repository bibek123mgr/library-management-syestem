package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.Config.AppLogger;
import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.BookDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.entity.Book;
import com.example.library.management.system.repo.BookRepo;
import com.example.library.management.system.service.BookService;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public  ResponseEntity<ResponseDtos<Void>> addNewBook(BookDtos bookDetails) {
        try {
            Book book = new Book();
            book.setAuthor(bookDetails.getAuthor());
            book.setTitle(bookDetails.getTitle());
            book.setIsbn(bookDetails.getIsbn());
            book.setQty(bookDetails.getQty());
            book.setIsAvailable(true);
            bookRepo.save(book);
            return ResponseUtils.getResponse(true, APIConstant.CREATED, HttpStatus.CREATED);
        } catch (Exception e) {
            AppLogger.error(e.getMessage(),e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<ResponseDtos<List<BookDtos>>> getAllBooksWithPagination() {
        try {
            List<Book> books = bookRepo.findAll();
            List<BookDtos> bookDtosList = books.stream()
                    .map(book -> mapToDto(book))
                    .collect(Collectors.toList());
            return ResponseUtils.getResponse(true, APIConstant.API_FETCH_SUCCESSFULLY, bookDtosList,HttpStatus.CREATED);
        } catch (Exception e) {
            AppLogger.error(e.getMessage(),e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR,new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<BookDtos>> getOneBookWithId(Integer id) {
        try {
            Optional<Book> book = bookRepo.findById(id);
            if (book.isEmpty()) {
                return ResponseUtils.getResponse(false, "Book not found",new BookDtos(), HttpStatus.BAD_REQUEST);
            }
            return ResponseUtils.getResponse(true, APIConstant.API_FETCH_SUCCESSFULLY,  mapToDto(book.get()),HttpStatus.CREATED);
        } catch (Exception e) {
            AppLogger.error(e.getMessage(),e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR,new BookDtos(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<ResponseDtos<Void>> updateBooksDetailsWithId(Integer id, BookDtos updatedBookDetails) {
        try {
            Optional<Book> bookOptional = bookRepo.findById(id);
            if (bookOptional.isEmpty()) {
                return ResponseUtils.getResponse(false, "Book not found", HttpStatus.BAD_REQUEST);
            }
            Book book = bookOptional.get();
            book.setAuthor(updatedBookDetails.getAuthor());
            book.setTitle(updatedBookDetails.getTitle());
            book.setIsbn(updatedBookDetails.getIsbn());
            book.setQty(updatedBookDetails.getQty());

            bookRepo.save(book);
            return ResponseUtils.getResponse(true, APIConstant.UPDATED, HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(e.getMessage(),e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> deleteBookFromRecord(Integer id) {
        try {
            if (!bookRepo.existsById(id)) {
                return ResponseUtils.getResponse(false, "Book not found", HttpStatus.BAD_REQUEST);
            }
            bookRepo.deleteById(id);
            return ResponseUtils.getResponse(true, APIConstant.DELETED, HttpStatus.OK);
        } catch (Exception e) {
            AppLogger.error(e.getMessage(),e);
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private BookDtos mapToDto(Book book) {
        BookDtos dto = new BookDtos();
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setQty(book.getQty());
        return dto;
    }
}
