package com.example.security.properties;


import lombok.Data;

/**
 * Socail 安全配置属性
 */
@Data
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private String socialRedirectUrl = "";

    private String socialBindUrl = "";

    private String socialRegistUrl = "";

    private WeiBoProperties weibo = new WeiBoProperties();


}
