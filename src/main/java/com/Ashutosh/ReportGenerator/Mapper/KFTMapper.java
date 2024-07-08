package com.Ashutosh.ReportGenerator.Mapper;


import com.Ashutosh.ReportGenerator.DTO.KFTDTO;

import com.Ashutosh.ReportGenerator.Entity.KFT;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class KFTMapper {
    @Autowired
    private ModelMapper modelMapper;

    public KFT KFTDTOtoKFT(KFTDTO kftdto){
        return modelMapper.map(kftdto,KFT.class);
    }

    public KFTDTO KFTtoKFTDTO(KFT kft){
        return modelMapper.map(kft,KFTDTO.class);
    }
}
