package com.example.library.management.system.service;

import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import org.springframework.http.ResponseEntity;

public interface AuthService {
   public ResponseEntity<ResponseDtos<Void>> signIn(LoginUserDtos loginData);

   public ResponseEntity<ResponseDtos<Void>> registerUser(UserDtos registerData);

   public  ResponseEntity<ResponseDtos<Void>> refreshToken(String refreshToken);
}
