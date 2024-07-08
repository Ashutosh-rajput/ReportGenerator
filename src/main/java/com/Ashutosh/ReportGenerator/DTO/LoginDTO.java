package com.Ashutosh.ReportGenerator.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}
