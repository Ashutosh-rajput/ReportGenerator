package com.Ashutosh.ReportGenerator.Service.ServiceImpl;

import com.Ashutosh.ReportGenerator.Entity.RefreshToken;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import com.Ashutosh.ReportGenerator.Repositry.RefreshTokenRepo;
import com.Ashutosh.ReportGenerator.Repositry.UserInfoRepo;
import com.Ashutosh.ReportGenerator.Service.ServiceInterface.RefreshTokenServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenServiceInterface {
    @Autowired
    private RefreshTokenRepo refreshTokenRepo;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public RefreshToken createRefreshToken(String username) {
        UserInfo userInfo = userInfoRepo.findByusername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Optional<RefreshToken> existingTokenOpt = refreshTokenRepo.findByUserInfo(userInfo);

        RefreshToken refreshToken;
        if (existingTokenOpt.isPresent()) {
            refreshToken = existingTokenOpt.get();
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setExpiryDate(Instant.now().plusMillis(600000));
        } else {
            refreshToken = RefreshToken.builder()
                    .userInfo(userInfo)
                    .token(UUID.randomUUID().toString())
                    .expiryDate(Instant.now().plusMillis(600000))
                    .build();
        }

        return refreshTokenRepo.save(refreshToken);
    }


//    public RefreshToken createRefreshToken(String username) {
//        RefreshToken refreshToken=RefreshToken.builder()
//                .userInfo(userInfoRepo.findByusername(username).orElseThrow(()-> new UsernameNotFoundException("user not found")))
//                .token(UUID.randomUUID().toString())
//                .expiryDate(Instant.now().plusMillis(600000))
//                .build();
//        return refreshTokenRepo.save(refreshToken);
//    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepo.findByToken(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken()+" Refresh Token was expired.");

        }
        return refreshToken;
    }


}
