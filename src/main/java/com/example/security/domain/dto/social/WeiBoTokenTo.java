package com.example.security.domain.dto.social;

import lombok.Data;

@Data
public class WeiBoTokenTo {
    /**
     * 授权用户的uid
     */
    private String uid;
    /**
     * access_token所属的应用appkey
     */
    private String appkey;
    /**
     * 用户授权的scope权限
     */
    private String scope;
    /**
     * access_token的创建时间，从1970年到创建时间的秒数
     */
    private String create_at;
    /**
     * access_token的剩余时间，单位是秒数。
     */
    private String expire_in;
}
