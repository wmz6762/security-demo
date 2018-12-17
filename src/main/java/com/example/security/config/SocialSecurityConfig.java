package com.example.security.config;

import com.example.security.handler.SECAuthenticationSuccessHandler;
import com.example.security.properties.SecurityProperties;
import com.example.security.social.SECSpringSocialConfigurer;
import com.example.security.social.SocialConnectionSignUp;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * social 配置类
 */
@Configuration
@EnableSocial
@Order(1)
public class SocialSecurityConfig extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SocialConnectionSignUp socialConnectionSignUp;
    @Autowired
    private SECAuthenticationSuccessHandler secAuthenticationSuccessHandler;

    /**
     * 配置连接到用户数据库
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setTablePrefix("t_");//设置连接表的前缀
        jdbcUsersConnectionRepository.setConnectionSignUp(socialConnectionSignUp);
        return jdbcUsersConnectionRepository;
    }

    /**
     * 个性化配置social
     *
     * @param securityProperties
     * @return
     */
    @Bean
    public SpringSocialConfigurer febsSocialSecurityConfig(SecurityProperties securityProperties) {
        SECSpringSocialConfigurer springSocialConfigurer = new SECSpringSocialConfigurer();
        springSocialConfigurer.setFebsSecurityProperties(securityProperties);
        springSocialConfigurer.setSecAuthenticationSuccessHandler(secAuthenticationSuccessHandler);
        return springSocialConfigurer;
    }

    /**
     * social 三方数据 上下文类
     *
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
        return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
    }



}
