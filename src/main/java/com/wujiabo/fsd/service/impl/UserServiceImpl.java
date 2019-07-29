package com.wujiabo.fsd.service.impl;

import com.wujiabo.fsd.entity.SysUser;
import com.wujiabo.fsd.mapper.UserMapper;
import com.wujiabo.fsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public SysUser loadUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<SysUser> getAllUsers() {
        return userMapper.findAll();
    }
}
