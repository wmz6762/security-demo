package com.example.security.config;


import com.example.security.properties.SecurityProperties;
import com.example.security.service.UserDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //获取可匿名访问路径
        String[] anonResourcesUrl = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getAnonResourcesUrl(), ",");

        http.formLogin()
                .and()
                .logout()
                .logoutUrl(securityProperties.getLogoutUrl())
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests().antMatchers(anonResourcesUrl).permitAll()
                .antMatchers(
                        securityProperties.getLoginUrl(),
                        securityProperties.getSocial().getSocialRedirectUrl(),
                        securityProperties.getSocial().getSocialBindUrl(),
                        securityProperties.getSocial().getSocialRegistUrl()
                ).permitAll()
//                .anyRequest().authenticated()
                .and().apply(socialSecurityConfig)
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html", "/static/**");
    }
}
