package com.wujiabo.fsd.config;

import com.wujiabo.fsd.exception.FSDException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ActionAdvice {
    @ExceptionHandler(FSDException.class)
    public ModelAndView bindFSDExceptionHandler(FSDException ex) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");
        mv.addObject("msg", ex.getMessage());
        return mv;
    }
}
