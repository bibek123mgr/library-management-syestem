package com.example.library.management.system.dtos;

import lombok.Data;

@Data
public class ValidateTokenRequestDtos {
    private Integer otp;
    private String email;
}
