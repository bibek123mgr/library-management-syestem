package com.example.library.management.system.utils;

import com.example.library.management.system.dtos.ResponseDtos;

public class ResponseUtils {

    public static <T> ResponseDtos<T> SUCCESS(String message) {
        return new ResponseDtos<>(true, message);
    }

    public static <T> ResponseDtos<T> ERROR(String message) {
        return new ResponseDtos<>(true, message);
    }

    public static <T> ResponseDtos<T> SUCCESS(String message, T data) {
        return new ResponseDtos<>(true, message, data);
    }

    public static ResponseDtos<Void> LOGIN_SUCCESSFULLY(String message, String accessToken, String refreshToken) {
        return new ResponseDtos<>(true, message, accessToken, refreshToken);
    }
}
