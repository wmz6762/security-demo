package com.example.security.social.weibo.config;

import com.example.security.properties.SecurityProperties;
import com.example.security.properties.WeiBoProperties;
import com.example.security.social.SocialConnectedView;
import com.example.security.social.weibo.connect.WeiBoConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.social.autoconfigure.SocialAutoConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 提供三方登录连接工厂的实例
 */
@Configuration
@ConditionalOnProperty(prefix = "sec.security.social.weibo", name = "app-id") //当sec.security.social.weibo.app-id 下面有值了才起到作用
@Order(2)
public class WeiBoAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WeiBoProperties weiBoProperties = securityProperties.getSocial().getWeibo();
        return new WeiBoConnectionFactory(weiBoProperties.getProviderId(), weiBoProperties.getAppId(), weiBoProperties.getAppSecret());
    }


    @Bean({"connect/weiboConnect", "connect/weiboConnected"})
    public View qqConnectedView() {
        return new SocialConnectedView();
    }
}
