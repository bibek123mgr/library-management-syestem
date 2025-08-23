package com.example.library.management.system.utils;

import com.example.library.management.system.dtos.ResponseDtos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    private ResponseUtils() {}

    public static ResponseEntity<ResponseDtos<Void>> getResponse(Boolean status, String message, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDtos<>(status, message), httpStatus);
    }

    public static ResponseEntity<ResponseDtos<Void>> getResponse(Boolean status, String message,String resetToken, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDtos<>(status, message,resetToken), httpStatus);
    }

    public static <T> ResponseEntity<ResponseDtos<T>> getResponse(Boolean status, String message, T data, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDtos<>(status, message, data), httpStatus);
    }


    public static ResponseEntity<ResponseDtos<Void>> getResponse(Boolean status, String message, String accessToken, String refreshToken, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ResponseDtos<>(status, message, accessToken, refreshToken), httpStatus);
    }
}
