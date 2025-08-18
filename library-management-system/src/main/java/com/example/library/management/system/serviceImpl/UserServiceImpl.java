package com.example.library.management.system.serviceImpl;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import com.example.library.management.system.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Override
    public ResponseDtos<Void> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos) {
       try{

           return new ResponseDtos<>(true,"Successfully Send OTP");
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public ResponseDtos<Void> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData) {
        try{
            return new ResponseDtos<>(true,"Successfully Validate OTP");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseDtos<Void> forgetPasswordRequest(String email) {
        try{
            return new ResponseDtos<>(true,"Successfully Password Changed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
