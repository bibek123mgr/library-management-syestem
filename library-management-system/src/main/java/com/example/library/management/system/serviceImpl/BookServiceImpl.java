package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.dtos.BookDtos;
import com.example.library.management.system.entity.Book;
import com.example.library.management.system.repo.BookRepo;
import com.example.library.management.system.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;

    @Override
    public String addNewBook(BookDtos bookDetails) {
        try {
            Book book = new Book();
            book.setAuthor(bookDetails.getAuthor());
            book.setTitle(bookDetails.getTitle());
            book.setIsbn(bookDetails.getIsbn());
            book.setQty(bookDetails.getQty());
            book.setIsAvailable(true);

            bookRepo.save(book);
            return "Book saved successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error while saving book", e);
        }
    }

    @Override
    public List<BookDtos> getAllBooksWithPagination() {
        try {
            List<Book> books = bookRepo.findAll();
            return books.stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching books", e);
        }
    }

    @Override
    public BookDtos getOneBookWithId(Integer id) {
        try {
            Optional<Book> book = bookRepo.findById(id);
            if (book.isEmpty()) {
                throw new RuntimeException("Book not found with ID: " + id);
            }
            return mapToDto(book.get());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching book by ID", e);
        }
    }

    @Override
    public String updateBooksDetailsWithId(Integer id, BookDtos updatedBookDetails) {
        try {
            Optional<Book> bookOptional = bookRepo.findById(id);
            if (bookOptional.isEmpty()) {
                throw new RuntimeException("Book not found with ID: " + id);
            }

            Book book = bookOptional.get();
            book.setAuthor(updatedBookDetails.getAuthor());
            book.setTitle(updatedBookDetails.getTitle());
            book.setIsbn(updatedBookDetails.getIsbn());
            book.setQty(updatedBookDetails.getQty());

            bookRepo.save(book);
            return "Book details updated successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error updating book details", e);
        }
    }

    @Override
    public String deleteBookFromRecord(Integer id) {
        try {
            if (!bookRepo.existsById(id)) {
                throw new RuntimeException("Book not found with ID: " + id);
            }
            bookRepo.deleteById(id);
            return "Book deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException("Error deleting book", e);
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
