package com.Ashutosh.ReportGenerator.Controllar;


import com.Ashutosh.ReportGenerator.DTO.LoginDTO;
import com.Ashutosh.ReportGenerator.Security.JwtService;
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

//    @Autowired
//    private LoginServiceImpl loginService;

    @PostMapping("/autologin")
    public String login(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(loginDTO.getUsername());
        }
        else {
            throw  new UsernameNotFoundException("invalid User Request");
        }


//        String token= loginService.login(loginDto);
//        JwtAuthResponseDto jwtAuthResponseDto=new JwtAuthResponseDto();
//        jwtAuthResponseDto.setAccessToken(token);
//        return new ResponseEntity<>(jwtAuthResponseDto, HttpStatus.ACCEPTED);

    }
}

