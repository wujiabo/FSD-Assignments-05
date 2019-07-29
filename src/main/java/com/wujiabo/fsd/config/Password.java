package com.wujiabo.fsd.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode("123456");
        System.out.println(encodedPassword);
    }
}
