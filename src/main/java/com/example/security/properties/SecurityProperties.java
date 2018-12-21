package com.example.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全配置属性
 */
@Data
@ConfigurationProperties(prefix = "sec.security")
public class SecurityProperties {
    private String loginUrl;
    private String logoutUrl;
    private String indexUrl;
    private String anonResourcesUrl;
    private SocialProperties social = new SocialProperties();
}
