package com.wujiabo.fsd.controller;

import com.xueqing.demo.springbootsecurity.security.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @PostMapping(value = "/logout")
    public String logout() {
        SecurityUtils.logout();
        return "redirect:login";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
