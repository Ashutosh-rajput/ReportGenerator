package com.Ashutosh.ReportGenerator.Controllar;

import com.Ashutosh.ReportGenerator.Entity.Result;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.ResultCBCServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/result")
public class ResultCBCController {
    @Autowired
    private ResultCBCServiceImpl resultCBCService;


    @DeleteMapping("/deletecbcresult/{id}")
    public ResponseEntity<String> deleteresult( @PathVariable Long id){
        resultCBCService.deleteResult(id);
        return ResponseEntity.ok("Deleted");

    }

    @GetMapping("/getresultcbcbyuserID/{id}")
    public ResponseEntity<Optional<Result>> detresultbyuserid(@PathVariable Long id){
        Optional<Result> result =resultCBCService.getResultbyUserID(id);
        return ResponseEntity.ok(result);


    }
}
