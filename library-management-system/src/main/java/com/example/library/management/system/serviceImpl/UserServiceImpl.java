package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import com.example.library.management.system.entity.User;
import com.example.library.management.system.repo.UserRepo;
import com.example.library.management.system.service.UserService;
import com.example.library.management.system.utils.ResponseUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public ResponseEntity<ResponseDtos<Void>> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos) {
       try{
           String username= resetPasswordRequestDtos.getEmail();
           User user=userRepo.findByEmail(username);
           if(user != null){

               return ResponseUtils.getResponse(true,"Successfully Send OTP", HttpStatus.OK);

           }else{

           }

           return ResponseUtils.getResponse(true,"Successfully Send OTP", HttpStatus.OK);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData) {
        try{
            return ResponseUtils.getResponse(true,"Successfully Validate OTP",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> forgetPasswordRequest(String email) {
        try{
            return ResponseUtils.getResponse(true,"Successfully Password Changed",HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
