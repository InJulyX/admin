package com.admin.config.shiro;

import com.admin.config.exception.UnauthorizedException;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends AccessControlFilter {
    protected static final String AUTHORIZATION_HEADER = "Authorization";

    private boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorization = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        return authorization != null;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (isLoginAttempt(servletRequest, servletResponse)) {
            JwtToken token = new JwtToken(request.getHeader(AUTHORIZATION_HEADER));
            try {
                getSubject(servletRequest, servletResponse).login(token);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
//        throw new NullPointerException();
        onLoginFail(servletResponse);
        return false;
    }

    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write("'o0o0o0o0o0o'");

    }
}
