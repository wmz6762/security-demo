package com.example.security.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class UserVo implements Serializable {

    private String id;
    private String name;
    private List<MenuVo> menus;

    public UserVo(String id,String name){
        this.id=id;
        this.name=name;
    }
}
