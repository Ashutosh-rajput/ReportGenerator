package com.Ashutosh.ReportGenerator.Service.ServiceInterface;

import com.Ashutosh.ReportGenerator.Entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenServiceInterface {
    RefreshToken createRefreshToken(String username);
    Optional<RefreshToken> findByToken(String token);
    RefreshToken verifyExpiration(RefreshToken token);
}
