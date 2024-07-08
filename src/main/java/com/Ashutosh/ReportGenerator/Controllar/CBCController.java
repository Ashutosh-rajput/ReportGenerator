package com.Ashutosh.ReportGenerator.Controllar;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.CBCServiceImpl;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.ResultCBCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CBC")
public class CBCController {
    @Autowired
    private CBCServiceImpl cbcService;
    @Autowired
    private ResultCBCServiceImpl resultService;

    @PostMapping("/create-cbc")
    public CBCDTO createCBC(@RequestBody CBCDTO cbcdto){
         resultService.createResult(cbcdto);
         return cbcService.createCBC(cbcdto);
    }

    @GetMapping("/get-cbcbyid/{id}")
    public ResponseEntity<CBCDTO> getcbcbyid(Long id){
        CBCDTO cbcdto=cbcService.getCBCbyid(id);
        return ResponseEntity.ok( cbcdto);
    }

    @PutMapping("/update-cbc/{id}")
    public ResponseEntity<CBCDTO> updateCBC(@RequestBody CBCDTO cbcdto,@PathVariable Long id){
        CBCDTO cbcdto1=cbcService.updateCBC(cbcdto,id);
        return ResponseEntity.ok(cbcdto1);
    }

    @DeleteMapping("/deletecbc/{id}")
    public ResponseEntity<String> deletecbcbyid(Long id){
        cbcService.deleteCBC(id);
        return ResponseEntity.ok( "Deleted");
    }
    @GetMapping("/get-cbcbyuserID/{id}")
    public ResponseEntity<CBCDTO> getCBCbyuuserID(Long id){
        CBCDTO cbcdto=cbcService.getCBCByUserID(id);
        return ResponseEntity.ok( cbcdto);

    }





}
