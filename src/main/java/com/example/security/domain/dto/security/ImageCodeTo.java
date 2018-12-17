package com.example.security.domain.dto.security;

import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
public class ImageCodeTo {
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;

    public ImageCodeTo(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public ImageCodeTo(BufferedImage image, String code, LocalDateTime expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = expireTime;
    }

    public boolean idExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
