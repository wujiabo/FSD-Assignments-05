package com.wujiabo.fsd.service;

import com.wujiabo.fsd.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class FsdUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.loadUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("Username Not Found Exception");
        }
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(sysUser.getRole());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(grantedAuthority);
        sysUser.setAuthorities(grantedAuthorities);
        return sysUser;
    }
}
