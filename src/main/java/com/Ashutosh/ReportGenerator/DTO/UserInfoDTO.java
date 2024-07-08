package com.Ashutosh.ReportGenerator.DTO;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserInfoDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String gender;
    private Long   age;
    private String Roles;

}
