package com.wujiabo.fsd.service;

import com.wujiabo.fsd.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class FsdUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userService.loadUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException("Username Not Found Exception");
        }
        return sysUser;
    }
}
