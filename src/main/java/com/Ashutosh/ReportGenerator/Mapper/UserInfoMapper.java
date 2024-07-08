package com.Ashutosh.ReportGenerator.Mapper;

import com.Ashutosh.ReportGenerator.DTO.UserInfoDTO;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public UserInfo userInfoDTOtouserInfo(UserInfoDTO userInfoDTO){
        UserInfo userInfo=modelMapper.map(userInfoDTO,UserInfo.class);
        return userInfo;
    }

    public UserInfoDTO userInfotouserInfoDTO(UserInfo userInfo){
        UserInfoDTO userInfoDTO=modelMapper.map(userInfo,UserInfoDTO.class);
        return userInfoDTO;
    }


}
