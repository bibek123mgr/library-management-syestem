package com.example.library.management.system.rest;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
public interface IssuedRecordRest {

    @PostMapping("/issue-books")
    public String createIssueRecord(@RequestBody IssuedRecordRest issuedRecordRest);

    @PutMapping("/issue-books")
    public String updateIssueRecord(@RequestBody IssuedRecordRest issuedRecordRest);

    @PatchMapping("/issue-books/{id}")
    public String returnedIssueRecord(@PathVariable Integer id);

    @GetMapping("/issue-books")
    public String getOneIssueRecord(@RequestBody IssuedRecordRest issuedRecordRest);

    @GetMapping("/issue-books")
    public String getAllIssueRecordPagination(@RequestBody IssuedRecordRest issuedRecordRest);

}
