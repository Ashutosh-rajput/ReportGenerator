package com.Ashutosh.ReportGenerator.Service.ServiceImpl;


//import com.Ashutosh.ReportGenerator.Config.CustomUserDetails;
import com.Ashutosh.ReportGenerator.Entity.UserInfo;
import com.Ashutosh.ReportGenerator.ExceptionHandler.ResourceNotFoundException;
import com.Ashutosh.ReportGenerator.Repositry.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomUserInfoDetailService implements UserDetailsService {
    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
//    @Cacheable(value = "userDetailsCache", key = "#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo= userInfoRepo.findByusername(username).orElseThrow(()->
                new ResourceNotFoundException("User with that Username doesn't exist "+ username));
        List<GrantedAuthority> authorities= Arrays.stream(userInfo.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
                username,
                userInfo.getPassword(),
                authorities
        );
//        return new CustomUserDetails(userInfo.getUsername(), userInfo.getPassword(), authorities);


//        new UserInfoUserDetails(userInfo);   **old method

    }
}