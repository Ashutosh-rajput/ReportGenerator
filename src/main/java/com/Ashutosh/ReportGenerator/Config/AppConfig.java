package com.Ashutosh.ReportGenerator.Config;


import com.Ashutosh.ReportGenerator.Controllar.CBCController;
import com.Ashutosh.ReportGenerator.Entity.Result;
import com.Ashutosh.ReportGenerator.Mapper.CBCMapper;
import com.Ashutosh.ReportGenerator.Mapper.UserInfoMapper;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.CBCServiceImpl;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.ResultCBCServiceImpl;
import com.Ashutosh.ReportGenerator.Service.ServiceImpl.UserInfoServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public UserInfoMapper userInfoMapper(){
        return new UserInfoMapper();
    }
    @Bean
    public UserInfoServiceImpl userInfoService(){
        return new UserInfoServiceImpl();
    }

    @Bean
    public CBCMapper cbcMapper(){
        return new CBCMapper();
    }
    @Bean
    public CBCServiceImpl cbcService(){
        return new CBCServiceImpl();
    }

    @Bean
    public ResultCBCServiceImpl resultCBCService(){
        return new ResultCBCServiceImpl();
    }






}
