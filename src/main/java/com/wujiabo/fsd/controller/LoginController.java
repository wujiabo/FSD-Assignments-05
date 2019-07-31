package com.wujiabo.fsd.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wujiabo.fsd.entity.SysUser;
import com.wujiabo.fsd.exception.FSDException;
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

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute SysUser sysUser, Model model,HttpServletRequest request) {
        if(StringUtils.isEmpty(sysUser.getUsername())){
            throw new FSDException("username is empty");
        }
        if(StringUtils.isEmpty(sysUser.getEmail())){
            throw new FSDException("email is empty");
        }
        if(StringUtils.isEmpty(sysUser.getName())){
            throw new FSDException("name is empty");
        }
        if(StringUtils.isEmpty(sysUser.getPassword())){
            throw new FSDException("new password is empty");
        }
        String s = request.getSession().getAttribute("CHECK_CODE").toString();
        if (StringUtils.isEmpty(sysUser.getKaptcha()) || !s.equals(sysUser.getKaptcha())) {
            throw new FSDException("kaptcha is incorrect");
        }

        SysUser existUser = userService.loadUserByUsername(sysUser.getUsername());
        if(existUser != null){
            throw new FSDException("username is exist");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        userService.register(sysUser);
        model.addAttribute("msg","register successful");
        return "register";
    }

    @GetMapping(value = "/register")
    public String toRegister(Model model) {
        model.addAttribute("sysUser",new SysUser());
        return "register";
    }

    @PostMapping(value = "/modify")
    public String modify(@ModelAttribute SysUser sysUser, Model model,HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        SysUser _sysUser = userService.loadUserByUsername(userDetails.getUsername());
        if(StringUtils.isEmpty(sysUser.getEmail())){
            throw new FSDException("email is empty");
        }
        if(StringUtils.isEmpty(sysUser.getName())){
            throw new FSDException("name is empty");
        }
        if(StringUtils.isEmpty(sysUser.getNewPassword())){
            throw new FSDException("new password is empty");
        }

        String s = request.getSession().getAttribute("CHECK_CODE").toString();
        if (StringUtils.isEmpty(sysUser.getKaptcha()) || !s.equals(sysUser.getKaptcha())) {
            throw new FSDException("kaptcha is incorrect");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        _sysUser.setEmail(sysUser.getEmail());
        _sysUser.setName(sysUser.getName());
        _sysUser.setPassword(passwordEncoder.encode(sysUser.getNewPassword()));
        userService.updateUser(_sysUser);
        model.addAttribute("msg","modify successful");
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


    @GetMapping("/captcha.jpg")
    public void applyCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = defaultKaptcha.createText();
        //生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);
        //保存到session
        request.getSession().setAttribute("CHECK_CODE", text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
