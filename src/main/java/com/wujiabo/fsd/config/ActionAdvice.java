package com.wujiabo.fsd.config;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ActionAdvice {
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public String  bindExceptionHandler(BindException ex) {
        return ex.getMessage();
    }
}
