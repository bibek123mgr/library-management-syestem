package com.example.library.management.system.restImpl;

import com.example.library.management.system.rest.IssuedRecordRest;
import com.example.library.management.system.service.IssuedRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IssuedRecordRestImpl implements IssuedRecordRest {

    @Autowired
    private IssuedRecordService issuedRecordService;

    @Override
    public String createIssueRecord(IssuedRecordRest issuedRecordRest) {
        try {
            return issuedRecordService
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }


    @Override
    public String updateIssueRecord(IssuedRecordRest issuedRecordRest) {
        try {
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String returnedIssueRecord(Integer id) {
        try {
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getOneIssueRecord(IssuedRecordRest issuedRecordRest) {
        try {
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getAllIssueRecordPagination(IssuedRecordRest issuedRecordRest) {
        try {
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
