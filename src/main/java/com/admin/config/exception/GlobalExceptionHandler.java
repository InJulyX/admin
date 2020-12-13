package com.admin.config.exception;

import com.admin.entity.BaseResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @ResponseBody
    @ExceptionHandler(TokenExpiredException.class)
    public Object tokenExpiredExceptionHandler() {
        return BaseResult.builder().code(401).msg("Token过期, 请重新登录");
    }

    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public void authorizationExceptionHandler(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write("{\"code\": 401,\"msg\": \"没有权限\"}");

//        return BaseResult.builder().code(401).msg("没有权限");
    }
}
