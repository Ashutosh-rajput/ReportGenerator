package com.Ashutosh.ReportGenerator.DTO;

import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CBCDTO {


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


    private UserInfo userInfo;

}
