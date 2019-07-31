package com.wujiabo.fsd.controller;

import com.wujiabo.fsd.entity.SysUser;
import com.wujiabo.fsd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.StringUtils;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:login";
    }

    @GetMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping(value = "/register")
    public String register() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping(value = "/modify")
    public String modify(@ModelAttribute SysUser sysUser,Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        SysUser _sysUser = userService.loadUserByUsername(userDetails.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(sysUser.getOldPassword());
        if(!StringUtils.equals(_sysUser.getPassword(),encodedPassword)){
            model.addAttribute("msg","old password is incorrect");
        }else{
            _sysUser.setEmail(sysUser.getEmail());
            _sysUser.setName(sysUser.getName());
            _sysUser.setPassword(passwordEncoder.encode(sysUser.getNewPassword()));
            userService.updateUser(_sysUser);
            model.addAttribute("msg","modify successful");
        }
        model.addAttribute("sysUser",userService.loadUserByUsername(userDetails.getUsername()));
        return "modify";
    }

    @GetMapping(value = "/modify")
    public String toModify(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        SysUser sysUser = userService.loadUserByUsername(userDetails.getUsername());
        model.addAttribute("sysUser", sysUser);
        return "modify";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
