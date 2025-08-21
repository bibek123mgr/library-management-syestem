package com.example.library.management.system.restImpl;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import com.example.library.management.system.rest.UserRest;
import com.example.library.management.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<ResponseDtos<Void>> forgetPasswordRequest(String email) {
        try{
           return userService.forgetPasswordRequest(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData) {
        try{
            return userService.verifyOTP(validateTokenRequestData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos) {
        try{
            return userService.resetPassword(resetPasswordRequestDtos);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
