package com.Ashutosh.ReportGenerator.DTO;

import lombok.*;

import java.security.PrivateKey;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JwtResponse {
    private String accessToken;
    private String token;
}
