package com.example.security.domain.vo;

import lombok.Data;

@Data
public class MenuVo {
    private int id;
    private String name;
    private String title;
    private String path;
    private String icon;

    public MenuVo(int id, String name, String title, String path, String icon){
        this.id=id;
        this.name=name;
        this.title=title;
        this.path=path;
        this.icon=icon;
    }
}
