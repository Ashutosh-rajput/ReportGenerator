package com.Ashutosh.ReportGenerator.Controllar;


import com.Ashutosh.ReportGenerator.DTO.JwtResponse;
import com.Ashutosh.ReportGenerator.DTO.LoginDTO;
import com.Ashutosh.ReportGenerator.DTO.RefreshTokenRequest;
import com.Ashutosh.ReportGenerator.Entity.RefreshToken;
import com.Ashutosh.ReportGenerator.ExceptionHandler.RefreshTokenNotFoundException;
import com.Ashutosh.ReportGenerator.Security.JwtService;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.RefreshTokenServiceImpl;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.TokenBlacklistServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
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

    @Autowired
    private TokenBlacklistServiceImpl tokenBlacklistService;

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
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("test1");
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            tokenBlacklistService.addToBlacklist(token);
            System.out.println("test2");
        }


        // Clear any session-related data if necessary

        return ResponseEntity.ok("Logged out successfully");
    }
    public String extractTokenFromRequest(HttpServletRequest request) {
        // Get the Authorization header from the request
        String authorizationHeader = request.getHeader("Authorization");

        // Check if the Authorization header is not null and starts with "Bearer "
        if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            // Extract the JWT token (remove "Bearer " prefix)
            return authorizationHeader.substring(7);
        }

        // If the Authorization header is not valid, return null
        return null;
    }
}

