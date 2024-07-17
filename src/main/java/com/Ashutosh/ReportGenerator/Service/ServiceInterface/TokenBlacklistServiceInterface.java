package com.Ashutosh.ReportGenerator.Service.ServiceInterface;

public interface TokenBlacklistServiceInterface {
    void addToBlacklist(String token);
    boolean isBlacklisted(String token);
}
