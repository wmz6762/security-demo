package com.example.security.domain.dto.social;

import lombok.Data;

@Data
public class WeiBoAccessTokenTo {
    private String access_token;
    private String expires_in;
    private String remind_in;
    private String uid;
}
