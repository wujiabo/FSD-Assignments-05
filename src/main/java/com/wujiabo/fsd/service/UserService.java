package com.wujiabo.fsd.service;

import com.wujiabo.fsd.entity.SysUser;

import java.util.List;

public interface UserService  {

     SysUser loadUserByUsername(String username);

    void updateUser(SysUser sysUser);
}
