package com.example.security.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "t_userconnection")
public class UserConnectionEntity {
    @Id
    private String userId;
    private String providerId;
    private String providerUserId;
    private String displayName;
    private String imageUrl;
    private String accessToken;
    private String refreshToken;
    private long expireTime;
}
