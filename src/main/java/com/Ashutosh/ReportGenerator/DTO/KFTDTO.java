package com.Ashutosh.ReportGenerator.DTO;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class KFTDTO {

    private Long kftId;
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

    private UserInfoDTO userInfoDTO;


}
