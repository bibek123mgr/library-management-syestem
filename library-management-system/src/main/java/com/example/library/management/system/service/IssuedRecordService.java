package com.example.library.management.system.service;

import com.example.library.management.system.dtos.IssueRecordDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.entity.IssueRecord;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IssuedRecordService {
   ResponseEntity<ResponseDtos<Void>> createIssueRecord(IssueRecordDtos issueRecordDtos);

   ResponseEntity<ResponseDtos<Void>> updateIssueRecord(IssueRecordDtos issueRecordDtos);

   ResponseEntity<ResponseDtos<IssueRecordDtos>> getOneIssueRecord(Integer id);

   ResponseEntity<ResponseDtos<List<IssueRecordDtos>>> getAllIssueRecordPagination();
}
