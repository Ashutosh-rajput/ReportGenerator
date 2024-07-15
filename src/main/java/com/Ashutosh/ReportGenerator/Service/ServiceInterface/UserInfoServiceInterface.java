package com.Ashutosh.ReportGenerator.Service.ServiceInterface;

import com.Ashutosh.ReportGenerator.DTO.UserInfoDTO;

import java.util.List;

public interface UserInfoServiceInterface {
    UserInfoDTO createUser(UserInfoDTO userInfoDTO);

    UserInfoDTO getuserbyid(Long id);

    List<UserInfoDTO> getallusers();

    UserInfoDTO updateuser(UserInfoDTO userInfoDTO, Long id);

    UserInfoDTO deleteuser(Long id);

    UserInfoDTO getUserByUsername(String username);

}
