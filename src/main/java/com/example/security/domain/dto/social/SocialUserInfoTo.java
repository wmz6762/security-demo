package com.example.security.domain.dto.social;

import lombok.Data;

/**
 * 三方登录用户信息
 */
@Data
public class SocialUserInfoTo {

    private String providerId;

    private String providerUserId;

    private String nickname;

    private String headImg;
}
