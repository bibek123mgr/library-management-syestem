package com.example.library.management.system.service;

import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;

public interface AuthService {
   public ResponseDtos<Void> signIn(LoginUserDtos loginData);

   public ResponseDtos<Void> registerUser(UserDtos registerData);

   public  ResponseDtos<Void> refreshToken(String refreshToken);
}
