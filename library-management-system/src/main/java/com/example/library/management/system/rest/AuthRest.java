package com.example.library.management.system.rest;

import com.example.library.management.system.dtos.LoginUserDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.UserDtos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthRest {

    @PostMapping("/register")
    public ResponseDtos<Void> registerUser(@RequestBody UserDtos registerData);

    @PostMapping("/sign-in")
    public ResponseDtos<Void> signIn(@RequestBody LoginUserDtos loginData);

    @PostMapping("/refresh-token")
    public ResponseDtos<Void> refreshToken(@RequestBody String refreshToken);


}
