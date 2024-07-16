package com.Ashutosh.ReportGenerator.ExceptionHandler;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
}
