package com.Ashutosh.ReportGenerator.Service.ServiceImpl;

import com.Ashutosh.ReportGenerator.DTO.UserInfoDTO;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ResourceNotFoundException;
import com.Ashutosh.ReportGenerator.ExceptionHandler.UsernameAlreadyExistsException;
import com.Ashutosh.ReportGenerator.Mapper.UserInfoMapper;
import com.Ashutosh.ReportGenerator.Repositry.UserInfoRepo;
import com.Ashutosh.ReportGenerator.Service.ServiceInterface.UserInfoServiceInterface;
import com.Ashutosh.ReportGenerator.Validation.TrackExecutiontime;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoServiceInterface {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @TrackExecutiontime
    @CachePut(value = "userinfo", key = "#result.id")
    public UserInfoDTO createUser(UserInfoDTO userInfoDTO) {
        if (userInfoRepo.findByusername(userInfoDTO.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username " + userInfoDTO.getUsername() + " already exists");
        }
        UserInfo userInfo = userInfoMapper.userInfoDTOtouserInfo(userInfoDTO);
        userInfo.setRoles("ROLE_USER");
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        UserInfo savedUserInfo = userInfoRepo.save(userInfo);
//        cacheManager.getCache("userinfo").put(savedUserInfo.getId(), userInfoMapper.userInfotouserInfoDTO(savedUserInfo));
        return userInfoMapper.userInfotouserInfoDTO(savedUserInfo);
    }

    @Override
    @Cacheable(value = "userinfo", key = "#id")
    public UserInfoDTO getuserbyid(Long id) {
        UserInfo userInfo = userInfoRepo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with that id doesn't exist: " + id));
        return userInfoMapper.userInfotouserInfoDTO(userInfo);
    }

//    @Override
//    @Cacheable(value = "allUsersCache",key="#page")
//    public List<UserInfoDTO> getallusers(Integer page) {
//        Page<UserInfo> users=userInfoRepo.findAll(PageRequest.of(--page,5));
//        List<UserInfoDTO> dtos = users.stream()
//                .map(userInfoMapper::userInfotouserInfoDTO)
//                .collect(Collectors.toList());
//        dtos.forEach(dto -> System.out.println(dto));  // Add logging to check the DTOs
//        return dtos;
////        return users.stream().map(user -> userInfoMapper.userInfotouserInfoDTO(user)).toList();
//
//    }
    @Override
    @Cacheable(value = "allUsersCache",key="'alluser'")
    public List<UserInfoDTO> getallusers() {
        List<UserInfo> users = userInfoRepo.findAll();
        List<UserInfoDTO> userDTOs = users.stream().map(userInfoMapper::userInfotouserInfoDTO).collect(Collectors.toList());
        return userDTOs;
    }

    @Override
    @CachePut(value = "userinfo", key = "#id")
    public UserInfoDTO updateuser(UserInfoDTO userInfoDTO, Long id) {
        UserInfo user = userInfoRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        user.setUsername(userInfoDTO.getUsername());
        user.setEmail(userInfoDTO.getEmail());
        user.setMobile(userInfoDTO.getMobile());
        UserInfo updateduser = userInfoRepo.save(user);
        return userInfoMapper.userInfotouserInfoDTO(updateduser);
    }

    @Override
    @CacheEvict(value = "userinfo", key = "#id")
    public UserInfoDTO deleteuser(Long id) {
        UserInfo user = userInfoRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        userInfoRepo.delete(user);
        return userInfoMapper.userInfotouserInfoDTO(user);
    }

    @Override
    @Cacheable(value = "userinfo", key = "#username")
    public UserInfoDTO getUserByUsername(String username) {
        UserInfo user = userInfoRepo.findByusername(username).orElseThrow(
                () -> new ResourceNotFoundException("User not found")
        );
        return userInfoMapper.userInfotouserInfoDTO(user);
    }
}
