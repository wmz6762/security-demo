package com.example.security.domain.dto.social;


import lombok.Data;

/**
 * 微博用户
 */
@Data
public class WeiBoUserTo {
    /**
     * string 类型id标识
     */
    private String idstr;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 位置
     */
    private String location;
    /**
     * 描述
     */
    private String description;
}
