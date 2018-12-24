package com.example.security.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SECAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private SessionRegistry sessionRegistry;
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
        String remoteAddress = details.getRemoteAddress();




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

//            String sessionId = details.getSessionId();
//            sessionRegistry.removeSessionInformation(sessionId);
//            sessionRegistry.registerNewSession(sessionId, authentication.getPrincipal());
//            ConcurrentSessionControlAuthenticationStrategy authenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
            // 手动设置最大并发登录数量，因为在 sessionManagement 中设置的 maximumSessions
            // 只对账号密码登录的方式生效 =。=
//            authenticationStrategy.onAuthentication(authentication, request, response);
//            request.getRequestDispatcher("http://127.0.0.1:8080/home").forward(request, response);
            redirectStrategy.sendRedirect(request, response,"http://127.0.0.1:8081/home");

        }

        response.setContentType("utf-8");
        response.getWriter().write("ok");
    }

    public SessionRegistry getSessionRegistry() {
        return sessionRegistry;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }



}
