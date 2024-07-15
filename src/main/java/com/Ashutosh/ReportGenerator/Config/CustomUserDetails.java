//package com.Ashutosh.ReportGenerator.Config;
////package com.Ashutosh.ReportGenerator.Security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class CustomUserDetails implements UserDetails {
//
//    private String username;
//    private String password;
//    private List<GrantedAuthority> authorities ;
//    private boolean accountNonExpired = true;
//    private boolean accountNonLocked = true;
//    private boolean credentialsNonExpired = true;
//    private boolean enabled = true;
//
//    public CustomUserDetails() {}
//
//    public CustomUserDetails(String username, String password, List<GrantedAuthority> authorities) {
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setAuthorities(List<GrantedAuthority> authorities) {
//        this.authorities = authorities;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setAccountNonExpired(boolean accountNonExpired) {
//        this.accountNonExpired = accountNonExpired;
//    }
//
//    public void setAccountNonLocked(boolean accountNonLocked) {
//        this.accountNonLocked = accountNonLocked;
//    }
//
//    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
//        this.credentialsNonExpired = credentialsNonExpired;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }
//}
