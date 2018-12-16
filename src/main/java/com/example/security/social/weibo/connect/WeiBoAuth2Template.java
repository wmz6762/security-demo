package com.example.security.social.weibo.connect;

import com.example.security.domain.dto.social.WeiBoAccessTokenTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

/**
 * 设置Auth请求配置
 */
public class WeiBoAuth2Template extends OAuth2Template {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public WeiBoAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

//
//    @Override
//    protected RestTemplate createRestTemplate() {
//        RestTemplate restTemplate = super.createRestTemplate();
//        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }

    /**
     * 获取ACCESS TOKEN 令牌 转成令牌
     * @param accessTokenUrl
     * @param parameters
     * @return
     */
    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {

        WeiBoAccessTokenTo result = this.getRestTemplate().postForObject(accessTokenUrl, parameters, WeiBoAccessTokenTo.class);
        log.info("--获取AccessToken信息--: {}", result);

        return new AccessGrant(result.getAccess_token(), null, null, Long.parseLong(result.getExpires_in()));
    }


}

