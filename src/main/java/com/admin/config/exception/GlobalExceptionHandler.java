package com.admin.config.exception;

import com.admin.entity.BaseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Object unauthorizedException() {
        return BaseResult.builder().code(10000).msg("用户名或密码错误").build();
    }
    @ResponseBody
    @ExceptionHandler(NumberFormatException.class)
    public Object numberFormatExceptionHandler() {
        return BaseResult.builder().code(10001).msg("缺少分页参数").build();
    }
}
