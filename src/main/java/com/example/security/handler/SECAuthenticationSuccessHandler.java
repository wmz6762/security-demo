package com.example.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SECAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();

        httpServletResponse.setContentType("utf-8");
        httpServletResponse.getWriter().write("ok");

        Object principal = authentication.getPrincipal();
//        if (principal instanceof FebsUserDetails) {
//            FebsUserDetails userDetails = (FebsUserDetails) principal;
//            userDetails.setRemoteAddress(remoteAddress);
//            loginType = userDetails.getLoginType();
//        }
//        if (principal instanceof SocialUs) {
//            FebsSocialUserDetails userDetails = (FebsSocialUserDetails) principal;
//            userDetails.setRemoteAddress(remoteAddress);
//            loginType = userDetails.getLoginType();
//        }

        if(principal instanceof SocialUserDetails){
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/");
        }
    }



}
