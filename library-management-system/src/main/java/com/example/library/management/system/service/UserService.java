package com.example.library.management.system.service;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;

public interface UserService {
   public ResponseDtos<Void> resetPassword(ResetPasswordRequestDtos resetPasswordRequestDtos);

   public ResponseDtos<Void> verifyOTP(ValidateTokenRequestDtos validateTokenRequestData);

   public ResponseDtos<Void> forgetPasswordRequest(String email);
}
