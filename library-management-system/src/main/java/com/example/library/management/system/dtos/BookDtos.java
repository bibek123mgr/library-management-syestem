package com.example.library.management.system.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDtos {

    private  Integer id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author must not exceed 255 characters")
    private String author;

    @NotBlank(message = "ISBN is required")
    @Size(max = 255, message = "ISBN must not exceed 255 characters")
    private String isbn;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer qty;

    private Boolean isAvailable;

    public BookDtos(){}

    public BookDtos(Integer id,String title,String author,String isbn,Integer qty,Boolean isAvailable){
        this.id=id;
        this.title=title;
        this.author=author;
        this.isbn=isbn;
        this.qty=qty;
        this.isAvailable=isAvailable;
    }
}
