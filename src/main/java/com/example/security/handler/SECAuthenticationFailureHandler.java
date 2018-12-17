package com.example.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SECAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Autowired
    ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        String message;
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException)
            message = "账户名或者密码输入错误!";
        else if (e instanceof LockedException)
            message = "账户被锁定";
        else
            message = "登录失败";
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(message));
    }
}
