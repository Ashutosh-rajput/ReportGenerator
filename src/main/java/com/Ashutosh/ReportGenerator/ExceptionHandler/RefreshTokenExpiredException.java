package com.Ashutosh.ReportGenerator.ExceptionHandler;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException(String message) {
        super(message);
    }
}
