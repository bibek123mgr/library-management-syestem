package com.example.library.management.system.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequestDtos {

    @NotBlank(message = "Email Address is Required")
    private String email;
}
