package com.example.library.management.system.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResetPasswordRequestDtos {

    @NotBlank(message = "Rest Token is Required")
    private String resetToken;

    @NotBlank(message = "New Password is Required")
    @Min(value = 8,message = "At lest 8 character is required")
    private String newPassword;

    @NotBlank(message = "New Confirm Password is Required")
    @Min(value = 8,message = "At lest 8 character is required")
    private String newConfirmPassword;


}
