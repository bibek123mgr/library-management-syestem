package com.example.library.management.system.rest;

import com.example.library.management.system.dtos.ResetPasswordRequestDtos;
import com.example.library.management.system.dtos.ResponseDtos;
import com.example.library.management.system.dtos.ValidateTokenRequestDtos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1")
public interface UserRest {

    @PostMapping("/forget-password-request")
    public ResponseDtos<Void> forgetPasswordRequest(@RequestBody String email);

    @PostMapping("/verify-otp")
    public ResponseDtos<Void> verifyOTP(@RequestBody ValidateTokenRequestDtos validateTokenRequestData);

    @PostMapping("/reset-password")
    public ResponseDtos<Void> resetPassword(@RequestBody ResetPasswordRequestDtos resetPasswordRequestDtos);
}
