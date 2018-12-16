package com.example.security.social;

import com.example.security.properties.SecurityProperties;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 配置social
 */
public class SECSpringSocialConfigurer extends SpringSocialConfigurer {
    private SecurityProperties securityProperties;
    /**
     * 修改social 默认配置
     * setFilterProcessesUrl 登录url组成由 socialAuthenticationFilter.setFilterProcessesUrl 和
     * （WeiBoAutoConfig weiBoProperties.getProviderId()）配置的ProviderId 名称 组成
     * @param object
     * @param <T>
     * @return
     */
    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter)super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(securityProperties.getSocial().getFilterProcessesUrl());// 三方登录请求的前半部分url
        socialAuthenticationFilter.setSignupUrl(securityProperties.getSocial().getSocialRedirectUrl());//三方获取完成后用户登录url
        return (T) socialAuthenticationFilter;
    }

    public void setFebsSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

}
