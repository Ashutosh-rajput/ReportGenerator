package com.Ashutosh.ReportGenerator.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "rseq")
    @SequenceGenerator(name = "rseq",sequenceName = "rex_seq",allocationSize = 1)
    private Long resultid;
    private String name;
    @Column(length = 2000)
    private List<String> Summary;
    @Column(length = 2000)
    private List<String> tips;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "Id")
    private UserInfo userInfo;
}
