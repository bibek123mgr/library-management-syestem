package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.constants.APIConstant;
import com.example.library.management.system.dtos.IssueRecordDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.entity.Book;
import com.example.library.management.system.entity.IssueRecord;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.repo.BookRepo;
import com.example.library.management.system.repo.IssuedRecordRepo;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.IssuedRecordService;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssuedRecordServiceImpl implements IssuedRecordService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private IssuedRecordRepo issuedRecordRepo;


    @Override
    public ResponseEntity<ResponseDtos<Void>> createIssueRecord(IssueRecordDtos issueRecordDtos) {
        try {

            IssueRecord record=new IssueRecord();
            record.setIssueDate(issueRecordDtos.getIssueDate());
            record.setDueDate(issueRecordDtos.getDueDate());
            record.setIsReturned(issueRecordDtos.getIsReturned());
            Optional<User> user= userRepo.findById(issueRecordDtos.getIssuedToId());
            Optional<Book> book= bookRepo.findById(issueRecordDtos.getBookId());
            if(user.isEmpty()){
                return ResponseUtils.getResponse(false,"Please Select The User To Whome Assigned", HttpStatus.BAD_REQUEST);
            }
            if(book.isEmpty()){
                return ResponseUtils.getResponse(false,"Please Select The Book ", HttpStatus.BAD_REQUEST);
            }
            issuedRecordRepo.save(record);
            return ResponseUtils.getResponse(false, APIConstant.CREATED,HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> updateIssueRecord(IssueRecordDtos issueRecordDtos) {
        try {
            Optional<IssueRecord> optionalIssueRecord= issuedRecordRepo.findById(issueRecordDtos.getId());
            if(optionalIssueRecord.isEmpty()){
                return ResponseUtils.getResponse(false, "No Record Found", HttpStatus.BAD_REQUEST);
            }
            IssueRecord record=optionalIssueRecord.get();
            record.setIssueDate(issueRecordDtos.getIssueDate());
            record.setDueDate(issueRecordDtos.getDueDate());
            record.setIsReturned(issueRecordDtos.getIsReturned());
            Optional<User> user= userRepo.findById(issueRecordDtos.getIssuedToId());
            Optional<Book> book= bookRepo.findById(issueRecordDtos.getBookId());
            if(user.isEmpty()){
                return ResponseUtils.getResponse(false,"Please Select The User To Whome Assigned", HttpStatus.BAD_REQUEST);
            }
            if(book.isEmpty()){
                return ResponseUtils.getResponse(false,"Please Select The Book ", HttpStatus.BAD_REQUEST);
            }
            issuedRecordRepo.save(record);
            return ResponseUtils.getResponse(false, APIConstant.CREATED,HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<IssueRecordDtos>> getOneIssueRecord(Integer id) {
        try {
            Optional<IssueRecord> optionalIssueRecord = issuedRecordRepo.findById(id);

            if (optionalIssueRecord.isEmpty()) {
                return ResponseUtils.getResponse(false, "No Record Found", new IssueRecordDtos(),HttpStatus.NOT_FOUND);
            }

            IssueRecordDtos recordDto = mapToIssueRecordDto(optionalIssueRecord.get());
            return ResponseUtils.getResponse(true, APIConstant.API_FETCH_SUCCESSFULLY, recordDto, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR,new IssueRecordDtos(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @Override
    public ResponseEntity<ResponseDtos<List<IssueRecordDtos>>> getAllIssueRecordPagination() {
        try {
            List<IssueRecord> optionalIssueRecord= issuedRecordRepo.findAll();
            if(optionalIssueRecord.isEmpty()){
                return ResponseUtils.getResponse(false, "No Record Found",new ArrayList<>(), HttpStatus.BAD_REQUEST);
            }
            List<IssueRecordDtos> issueRecordDtosList=optionalIssueRecord.stream().map(this::mapToIssueRecordDto).toList();
            return ResponseUtils.getResponse(true, APIConstant.API_FETCH_SUCCESSFULLY, issueRecordDtosList, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseUtils.getResponse(false, APIConstant.INTERNAL_SERVER_ERROR,new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private IssueRecordDtos mapToIssueRecordDto(IssueRecord record) {
        return new IssueRecordDtos(
                record.getId(),
                record.getIssueDate(),
                record.getDueDate(),
                record.getReturnedDate(),
                record.getUser().getId(),
                record.getUser().getName(),
                record.getBook().getId(),
                record.getBook().getTitle(),
                record.getIsReturned()
        );
    }

}
