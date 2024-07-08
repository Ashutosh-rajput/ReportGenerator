package com.Ashutosh.ReportGenerator.Service.ServiceImpl;

import com.Ashutosh.ReportGenerator.DTO.UserInfoDTO;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ResourceNotFoundException;
import com.Ashutosh.ReportGenerator.ExceptionHandler.UsernameAlreadyExistsException;
import com.Ashutosh.ReportGenerator.Mapper.UserInfoMapper;
import com.Ashutosh.ReportGenerator.Repositry.UserInfoRepo;
import com.Ashutosh.ReportGenerator.Service.ServiceInterface.UserInfoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoServiceInterface {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        if (userInfoRepo.findByusername(userInfoDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + userInfoDTO.getUsername() + " already exists");
        }
        UserInfo userInfo=userInfoMapper.userInfoDTOtouserInfo(userInfoDTO);
        userInfo.setRoles("ROLE_USER");
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUserInfo=userInfoRepo.save(userInfo);
        return userInfoMapper.userInfotouserInfoDTO(savedUserInfo);
    }

    @Override
    public UserInfoDTO getuserbyid(Long id) {
        UserInfo userInfo=userInfoRepo.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User with that id doesnt exist"+ id));
        return userInfoMapper.userInfotouserInfoDTO(userInfo);
    }

    @Override
    public List<UserInfoDTO> getallusers() {
        List<UserInfo> users=userInfoRepo.findAll();
        return users.stream().map((user)->userInfoMapper.userInfotouserInfoDTO(user)).toList();

    }

    @Override
    public UserInfoDTO updateuser(UserInfoDTO userInfoDTO, Long id) {
        UserInfo user=userInfoRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        user.setUsername(userInfoDTO.getUsername());
        user.setEmail(userInfoDTO.getEmail());
        user.setMobile(userInfoDTO.getMobile());
        UserInfo updateduser=userInfoRepo.save(user);
        return userInfoMapper.userInfotouserInfoDTO(updateduser);
    }

    @Override
    public UserInfoDTO deleteuser(Long id) {
        UserInfo user=userInfoRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        userInfoRepo.delete(user);
        return userInfoMapper.userInfotouserInfoDTO(user);
    }

    @Override
    public UserInfoDTO getUserByUsername(String username) {
        UserInfo user=userInfoRepo.findByusername(username).orElseThrow(
                ()->new ResourceNotFoundException("user not found")
        );
        return userInfoMapper.userInfotouserInfoDTO(user);
    }
}
