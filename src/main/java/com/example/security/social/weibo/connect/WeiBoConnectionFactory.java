package com.example.security.social.weibo.connect;

import com.example.security.social.weibo.api.WeiBo;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 连接工厂类实例
 */
public class WeiBoConnectionFactory extends OAuth2ConnectionFactory<WeiBo> {

    public WeiBoConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiBoServiceProvider(appId, appSecret), new WeiBoAdapter());
    }
}
