package com.example.security.controller;

import com.example.security.domain.dto.social.SocialUserInfoTo;
import com.example.security.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/")
public class SocialController {
    @Autowired
    private ProviderSignInUtils providerSignInUtils;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private ObjectMapper objectMapper;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 社交账户登录
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping("social")
    public void Social(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SocialUserInfoTo socialUserInfoTo = new SocialUserInfoTo();
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        if (connection == null) {
            redirectStrategy.sendRedirect(request, response, securityProperties.getLoginUrl());
        } else {
            socialUserInfoTo.setProviderId(connection.getKey().getProviderId());
            socialUserInfoTo.setProviderUserId(connection.getKey().getProviderUserId());
            socialUserInfoTo.setHeadImg(connection.getImageUrl());
            socialUserInfoTo.setNickname(connection.getDisplayName());
        }
        response.getWriter().write(objectMapper.writeValueAsString(socialUserInfoTo));

        providerSignInUtils.doPostSignUp("张三", new ServletWebRequest(request));

        redirectStrategy.sendRedirect(request,response,"/");
    }

    /**
     * 社交账户绑定
     */
    @GetMapping("/social/bind")
    public void socialBind(HttpServletRequest request){
        providerSignInUtils.doPostSignUp("张三", new ServletWebRequest(request));
    }

    /**
     * 社交账户注册
     */
    public void socialRegist(){

    }



}
