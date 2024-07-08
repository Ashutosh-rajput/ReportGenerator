package com.Ashutosh.ReportGenerator.Entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    private   Long       id;
    private String username;
    private String password;
    private String    email;
    private String   mobile;
    private String   gender;
    private   Long      age;
    private String Roles;

}
