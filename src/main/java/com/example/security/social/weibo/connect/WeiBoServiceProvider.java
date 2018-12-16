package com.example.security.social.weibo.connect;

import com.example.security.domain.constant.SecurityConstant;
import com.example.security.social.weibo.api.WeiBo;
import com.example.security.social.weibo.api.WeiBoImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 三方连接实例
 */
public class WeiBoServiceProvider extends AbstractOAuth2ServiceProvider<WeiBo> {

    public WeiBoServiceProvider(String appid, String appSecret) {
        super(new WeiBoAuth2Template(appid, appSecret, SecurityConstant.Get_WeiBo_Authorize_url, SecurityConstant.Get_Weibo_AccessToken_Url));
    }

    @Override
    public WeiBo getApi(String accessToken) {
        return new WeiBoImpl(accessToken);
    }
}
