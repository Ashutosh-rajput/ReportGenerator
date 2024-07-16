package com.Ashutosh.ReportGenerator.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserInfoDTO  {
    private Long id;
    @NotNull(message = "Username Shouldn't be null")
    private String username;
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{10}$",message = "invalid moblie number entered")
    private String mobile;
    private String gender;
    @Min(16)
    @Max(70)
    private Long   age;
    private String roles;

}
