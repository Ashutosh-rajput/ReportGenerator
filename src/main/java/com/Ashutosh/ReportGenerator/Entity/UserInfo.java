package com.Ashutosh.ReportGenerator.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class UserInfo implements Serializable  {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "useq")
    @SequenceGenerator(name = "useq",sequenceName = "uex_seq",allocationSize = 1)
    private   Long       id;
    private String username;
    private String password;
    private String    email;
    private String   mobile;
    private String   gender;
    private   Long      age;
    private String    roles;

}
