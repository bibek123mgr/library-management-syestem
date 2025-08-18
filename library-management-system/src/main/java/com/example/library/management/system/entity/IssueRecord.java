package com.example.library.management.system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "issue_record")
public class IssueRecord {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;


        private LocalDate issueDate;
        private LocalDate dueDate;
        private LocalDate returnedDate;


        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "book_id")
        private Book book;
        private Boolean isReturned;


    }
