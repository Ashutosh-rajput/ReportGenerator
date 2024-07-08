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
public class KFT {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    private Long kftid;
    private Long urea;
    private Long creatinine;
    private Long uricacid;
    private Long calcium;
    private Long phosphorus;
    private Long alkalinephosphates;
    private Long totalprotein;
    private Long albumin;
    private Long sodium;
    private Long potasssium;
    private Long chloride;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private UserInfo userInfo;


}
