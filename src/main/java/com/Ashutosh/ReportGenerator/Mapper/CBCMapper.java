package com.Ashutosh.ReportGenerator.Mapper;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;
import com.Ashutosh.ReportGenerator.Entity.CBC;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.constant.ModuleDesc;

public class CBCMapper {
    @Autowired
    private ModelMapper modelMapper;

    public CBC CBCDTOtoCBC(CBCDTO cbcdto){
        return modelMapper.map(cbcdto,CBC.class);
    }

    public CBCDTO CBCtoCBCDTO(CBC cbc){
        return modelMapper.map(cbc,CBCDTO.class);
    }
}
