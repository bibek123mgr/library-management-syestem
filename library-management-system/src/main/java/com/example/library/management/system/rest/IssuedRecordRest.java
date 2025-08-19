package com.example.library.management.system.rest;

import com.example.library.management.system.dtos.IssueRecordDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.entity.IssueRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
public interface IssuedRecordRest {

    @PostMapping("/issue-books")
    public ResponseEntity<ResponseDtos<Void>> createIssueRecord(@RequestBody IssueRecordDtos issueRecordDtos);

    @PutMapping("/issue-books")
    public ResponseEntity<ResponseDtos<Void>> updateIssueRecord(@RequestBody IssueRecordDtos issueRecordDtos);


    @GetMapping("/issue-books/{id}")
    public ResponseEntity<ResponseDtos<IssueRecordDtos>> getOneIssueRecord(@PathVariable Integer id);

    @GetMapping("/issue-books")
    public ResponseEntity<ResponseDtos<List<IssueRecordDtos>>> getAllIssueRecordPagination();

}
