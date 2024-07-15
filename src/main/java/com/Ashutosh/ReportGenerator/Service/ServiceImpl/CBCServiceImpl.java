package com.Ashutosh.ReportGenerator.Service.ServiceImpl;

import com.Ashutosh.ReportGenerator.DTO.CBCDTO;
import com.Ashutosh.ReportGenerator.Entity.CBC;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ResourceNotFoundException;
import com.Ashutosh.ReportGenerator.Mapper.CBCMapper;
import com.Ashutosh.ReportGenerator.Mapper.UserInfoMapper;
import com.Ashutosh.ReportGenerator.Repositry.CBCRepo;
import com.Ashutosh.ReportGenerator.Repositry.UserInfoRepo;
import com.Ashutosh.ReportGenerator.Service.ServiceInterface.CBCServiceInterface;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CBCServiceImpl implements CBCServiceInterface {
    @Autowired
    private CBCMapper cbcMapper;
    @Autowired
    private CBCRepo cbcRepo;
    @Override
    @CachePut(value = "CBC", key = "#result.cbcid")
    public CBCDTO createCBC(CBCDTO cbcdto) {
        CBC cbc=cbcMapper.CBCDTOtoCBC(cbcdto);
        return cbcMapper.CBCtoCBCDTO(cbcRepo.save(cbc));
    }

    @Override
    @Cacheable(value = "CBC",key="#id")
    public CBCDTO getCBCbyid(Long id) {
        CBC cbc=cbcRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("CBC Report with id doesnt exist"+ id));
        return cbcMapper.CBCtoCBCDTO(cbc);
    }

    @Override
    @CachePut(value = "CBC",key = "#id")
    public CBCDTO updateCBC(CBCDTO cbcdto, Long id) {
        CBC cbc=cbcRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("CBC Report with id doesnt exist"+ id));
        cbc.setHemoglobin(cbcdto.getHemoglobin());
        cbc.setRbccount(cbcdto.getRbccount());
        cbc.setPackedcellvolume(cbcdto.getPackedcellvolume());
        cbc.setMeancorpuscularvolume(cbcdto.getMeancorpuscularvolume());
        cbc.setMch(cbcdto.getMch());
        cbc.setMchc(cbcdto.getMchc());
        cbc.setRdw(cbcdto.getRdw());
        cbc.setWbccount(cbcdto.getWbccount());
        cbc.setNeutrophils(cbcdto.getNeutrophils());
        cbc.setLymphocytces(cbcdto.getLymphocytces());
        cbc.setEosinophils(cbcdto.getEosinophils());
        cbc.setMonocytes(cbcdto.getMonocytes());
        cbc.setBasophils(cbcdto.getBasophils());
        cbc.setPlateletcount(cbcdto.getPlateletcount());
        return cbcMapper.CBCtoCBCDTO(cbcRepo.save(cbc));
    }

    @Override
    @CacheEvict(value = "CBC",key = "#id")
    public CBCDTO deleteCBC(Long id) {
        CBC cbc=cbcRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("CBC Report with id doesnt exist"+ id));
        cbcRepo.delete(cbc);
        return cbcMapper.CBCtoCBCDTO(cbc);
    }

    @Override
    @Cacheable(value="userCBC",key="#id")
    public CBCDTO getCBCByUserID(Long id) {
        CBC cbc=cbcRepo.findByUserInfo_Id(id);
        return cbcMapper.CBCtoCBCDTO(cbc);
    }
}
