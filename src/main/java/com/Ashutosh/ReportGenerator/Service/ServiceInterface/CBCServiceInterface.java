package com.Ashutosh.ReportGenerator.Service.ServiceInterface;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;

import java.util.List;

public interface CBCServiceInterface {
    CBCDTO createCBC(CBCDTO cbcdto);
    CBCDTO getCBCbyid(Long id);
//    List<CBCDTO> getallusers();
    CBCDTO updateCBC(CBCDTO cbcdto, Long id);
    CBCDTO deleteCBC(Long id);
    CBCDTO getCBCByUserID(Long id);
}
