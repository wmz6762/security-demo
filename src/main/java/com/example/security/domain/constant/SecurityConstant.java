package com.example.security.domain.constant;


public class SecurityConstant {

    /**
     * 获取用户信息
     * GET
     * access_token
     * uid
     */
    public static final String Get_Weibo_UserInfo_Url = "https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s";
    /**
     * 获取AccessToken 信息
     */
    public static final String Get_Weibo_AccessToken_Url = "https://api.weibo.com/oauth2/access_token";

    /**
     * 查询用户access_token的授权相关信息，包括授权时间，过期时间和scope权限。
     * POST
     * access_token：用户授权时生成的access_token
     */
    public static final String Get_Weibo_TokenInfo_url = "https://api.weibo.com/oauth2/get_token_info?access_token=%s";

    /**
     * 获取登录code 接口
     */
    public static final String Get_WeiBo_Authorize_url = "https://api.weibo.com/oauth2/authorize";




}
