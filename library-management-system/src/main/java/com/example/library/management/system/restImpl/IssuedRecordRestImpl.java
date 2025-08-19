package com.example.library.management.system.restImpl;

import com.example.library.management.system.dtos.IssueRecordDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.entity.IssueRecord;
import com.example.library.management.system.rest.IssuedRecordRest;
import com.example.library.management.system.service.IssuedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IssuedRecordRestImpl implements IssuedRecordRest {

    @Autowired
    private IssuedRecordService issuedRecordService;

    @Override
    public ResponseEntity<ResponseDtos<Void>> createIssueRecord(IssueRecordDtos issueRecordDtos) {
        try {
            return issuedRecordService.createIssueRecord(issueRecordDtos);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


    @Override
    public ResponseEntity<ResponseDtos<Void>> updateIssueRecord(IssueRecordDtos issueRecordDtos) {
        try {
            return issuedRecordService.updateIssueRecord(issueRecordDtos);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

//    @Override
//    public ResponseEntity<ResponseDtos<Void>> returnedIssueRecord(Integer id) {
//        try {
//            return issuedRecordService.returnedIssueRecord(id);
//
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }

    @Override
    public ResponseEntity<ResponseDtos<IssueRecordDtos>> getOneIssueRecord(Integer id) {
        try {
            return issuedRecordService.getOneIssueRecord(id);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<List<IssueRecordDtos>>> getAllIssueRecordPagination() {
        try {
            return issuedRecordService.getAllIssueRecordPagination();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
