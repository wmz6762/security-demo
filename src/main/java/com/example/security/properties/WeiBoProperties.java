package com.example.security.properties;


import org.springframework.social.autoconfigure.SocialProperties;
/**
 * 微博三方登录配置属性
 */

public class WeiBoProperties extends  SocialProperties {
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    private String providerId = "weibo";
}
