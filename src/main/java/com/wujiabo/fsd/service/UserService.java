package com.wujiabo.fsd.service;

import com.wujiabo.fsd.entity.SysUser;

public interface UserService  {

     SysUser loadUserByUsername(String username);

    void updateUser(SysUser sysUser);

    void register(SysUser sysUser);
}
