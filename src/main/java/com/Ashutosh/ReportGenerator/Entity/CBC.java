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
public class CBC {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq")
    @SequenceGenerator(name = "seq",sequenceName = "ex_seq",allocationSize = 1)
    private Long cbcid;
    private Long hemoglobin;
    private Long rbccount;
    private Long packedcellvolume;
    private Long meancorpuscularvolume;
    private Long mch;
    private Long mchc;
    private Long rdw;
    private Long wbccount;
    private Long neutrophils;
    private Long lymphocytces;
    private Long eosinophils;
    private Long monocytes;
    private Long basophils;
    private Long plateletcount;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "Id")
    private UserInfo userInfo;

}
