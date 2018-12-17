package com.example.security.config;


import com.example.security.filter.KaptchaAuthenticationFilter;
import com.example.security.handler.SECAuthenticationAccessDeniedHandler;
import com.example.security.handler.SECAuthenticationFailureHandler;
import com.example.security.handler.SECAuthenticationSuccessHandler;
import com.example.security.properties.SecurityProperties;
import com.example.security.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter implements AuthenticationEntryPoint {

    @Autowired
    AccessDecisionManagerConf accessDecisionManagerConf;
    @Autowired
    InvocationSecurityMetadataSourceConf invocationSecurityMetadataSourceConf;



    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private SECAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private SECAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    SECAuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    KaptchaAuthenticationFilter kaptchaAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //获取可匿名访问路径
        String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getAnonResourcesUrl(), ",");

        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(invocationSecurityMetadataSourceConf);
                o.setAccessDecisionManager(accessDecisionManagerConf);
                return o;
            }
        })
                .and().formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()

                .logout()
                .logoutUrl(securityProperties.getLogoutUrl())
                .logoutSuccessUrl(securityProperties.getWebUrl())
                .and()
                .authorizeRequests().antMatchers(anonResourcesUrl).permitAll()
                .antMatchers(
                        securityProperties.getWebUrl(),
                        securityProperties.getSocial().getSocialRedirectUrl(),
                        securityProperties.getSocial().getSocialBindUrl(),
                        securityProperties.getSocial().getSocialRegistUrl()
                ).permitAll()
                .anyRequest().authenticated()
                .and().apply(socialSecurityConfig)
                .and().exceptionHandling().authenticationEntryPoint(this)
                .and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler)
                .and()
                .addFilterBefore(kaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if (isAjaxRequest(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } else {
            response.sendRedirect(securityProperties.getLoginUrl());
        }
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }

}
