package com.example.library.management.system.service;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import org.springframework.http.ResponseEntity;

public interface UserService {
   public ResponseEntity<ResponseDtos<Void>> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos);

   public ResponseEntity<ResponseDtos<Void>> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData);

   public ResponseEntity<ResponseDtos<Void>> forgetPasswordRequest(String email);
}
