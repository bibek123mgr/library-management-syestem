package com.example.library.management.system.restImpl;

import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import com.example.library.management.system.rest.AuthRest;
import com.example.library.management.system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthRestImpl implements AuthRest {

    @Autowired
    private AuthService authService;

    @Override
    public ResponseEntity<ResponseDtos<Void>> registerUser(UserDtos registerData) {
        try {
            return authService.registerUser(registerData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> signIn(LoginUserDtos loginData) {
        try {
            return authService.signIn(loginData);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<ResponseDtos<Void>> refreshToken(String refreshToken) {
        try {
            return authService.refreshToken(refreshToken);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
