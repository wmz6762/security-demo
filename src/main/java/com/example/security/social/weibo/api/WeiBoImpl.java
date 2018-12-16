package com.example.security.social.weibo.api;

import com.example.security.domain.constant.SecurityConstant;
import com.example.security.domain.dto.social.WeiBoTokenTo;
import com.example.security.domain.dto.social.WeiBoUserTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * 获取微博用户信息实现类
 */
public class WeiBoImpl  extends AbstractOAuth2ApiBinding implements WeiBo {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private ObjectMapper objectMapper = new ObjectMapper();


    private String uid;
    private String accessToken;

    public WeiBoImpl(String accessToken)  {
        //TokenStrategy.ACCESS_TOKEN_PARAMETER 设置请求参数放在url中
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken = accessToken;
        String url = String.format(SecurityConstant.Get_Weibo_TokenInfo_url, accessToken);
        try{
            WeiBoTokenTo tokenInfo= this.getRestTemplate().postForObject(url,null,WeiBoTokenTo.class);
            log.info("--获取accessToken 里的用户信息--:" + objectMapper.writeValueAsString(tokenInfo));
            this.uid = tokenInfo.getUid();
        }catch (Exception e){
            log.info("--获取accessToken用户信息失败--:");
        }

    }

    @Override
    public WeiBoUserTo getUserInfo() {
        String url = String.format(SecurityConstant.Get_Weibo_UserInfo_Url, this.accessToken, this.uid);
        WeiBoUserTo weiBoUserTo = this.getRestTemplate().getForObject(url, WeiBoUserTo.class);
        try {
            log.info("--用户微博用户信息--:" + objectMapper.writeValueAsString(weiBoUserTo));

        } catch (Exception e) {
            log.info("获取用户信息失败");
        }
        return weiBoUserTo;
    }
}
