package com.Ashutosh.ReportGenerator.Repositry;

import com.Ashutosh.ReportGenerator.Entity.RefreshToken;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserInfo(UserInfo userInfo);
}
