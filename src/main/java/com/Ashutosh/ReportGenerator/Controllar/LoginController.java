package com.Ashutosh.ReportGenerator.Controllar;


import com.Ashutosh.ReportGenerator.DTO.JwtResponse;
import com.Ashutosh.ReportGenerator.DTO.LoginDTO;
import com.Ashutosh.ReportGenerator.DTO.RefreshTokenRequest;
import com.Ashutosh.ReportGenerator.Entity.RefreshToken;
import com.Ashutosh.ReportGenerator.ExceptionHandler.RefreshTokenNotFoundException;
import com.Ashutosh.ReportGenerator.Security.JwtService;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.RefreshTokenServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@EnableMethodSecurity
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public JwtService jwtService;

    @Autowired
    private RefreshTokenServiceImpl  refreshTokenService;

//    @Autowired
//    private LoginServiceImpl loginService;

    @PostMapping("/autologin")
    public JwtResponse login(@RequestBody @Valid LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginDTO.getUsername());
            return JwtResponse.builder()
                    .accessToken(jwtService.generateToken(loginDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
        }
        else {
            throw  new UsernameNotFoundException("invalid User Request");
        }
//        String token= loginService.login(loginDto);
//        JwtAuthResponseDto jwtAuthResponseDto=new JwtAuthResponseDto();
//        jwtAuthResponseDto.setAccessToken(token);
//        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.ACCEPTED);
    }

    @PostMapping("/refreshtoken")
    public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return refreshTokenService.findByToken(refreshTokenRequest.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken=jwtService.generateToken(userInfo.getUsername());
                    return JwtResponse.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(()->new RefreshTokenNotFoundException(
                        "Refresh token is not in database"));

    }
}

