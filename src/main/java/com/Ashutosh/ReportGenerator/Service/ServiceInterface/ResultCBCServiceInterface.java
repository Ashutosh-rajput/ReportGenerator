package com.Ashutosh.ReportGenerator.Service.ServiceInterface;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;
import com.Ashutosh.ReportGenerator.Entity.Result;

import java.util.Optional;

public interface ResultCBCServiceInterface {
    Result createResult(CBCDTO cbcdto);
    Result deleteResult(Long id);
    Optional<Result> getResultbyUserID(Long id);



//    CBCDTO getCBCbyid(Long id);
    //    List<CBCDTO> getallusers();
//    CBCDTO updateCBC(CBCDTO cbcdto, Long id);

}
