package com.example.library.management.system.dtos;

import lombok.Data;

@Data
public class ResponseDtos<T> {

    private Boolean status;
    private String message;
    private T data;
    private String accessToken;
    private String refreshToken;
    private String resetToken;

    public ResponseDtos() {}

    public ResponseDtos(Boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseDtos(Boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseDtos(Boolean status, String message, String resetToken) {
        this.status = status;
        this.message = message;
        this.resetToken = resetToken;
    }

    
    public ResponseDtos(Boolean status, String message, String accessToken, String refreshToken) {
        this.status = status;
        this.message = message;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
