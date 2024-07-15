package com.Ashutosh.ReportGenerator.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserInfoDTO  {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String gender;
    private Long   age;
    private String roles;

}
