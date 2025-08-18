package com.example.library.management.system.dtos;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IssueRecordDtos {

    private Integer id;

    @NotNull(message = "issueDate is Required")
    private LocalDate issueDate;

    @NotNull(message = "dueDate is Required")
    private LocalDate dueDate;

    @NotNull(message = "returnedDate is Required")
    private LocalDate returnedDate;

    private Integer issuedToId;
    private String issuedToName;

    @NotNull(message = "book is Required")
    private Integer bookId;

    private String bookTitle;

    public IssueRecordDtos(){};

    public IssueRecordDtos(Integer id, LocalDate issueDate, LocalDate dueDate, LocalDate returnedDate, Integer issuedToId, String issuedToName, Integer bookId, String bookTitle) {
        this.id = id;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
        this.issuedToId = issuedToId;
        this.issuedToName = issuedToName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }
}
