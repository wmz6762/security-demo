package com.example.security.controller;

import com.example.security.domain.vo.MenuVo;
import com.example.security.domain.dto.sys.UserTo;
import com.example.security.domain.vo.UserVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping
    public UserVo getCurrentUser() throws JsonProcessingException {

        List<MenuVo> menus=new ArrayList<>();
        menus.add(new MenuVo(1,"home","主页", "/","#icon-home"));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserTo) {
            UserTo model = (UserTo) authentication.getPrincipal();
            menus.add(new MenuVo(1,"menu1","菜单1", "/menu1","#icon-gift"));
            UserVo userVo=new UserVo(String.valueOf(model.getId()),model.getUsername());
            userVo.setMenus(menus);
            return userVo;

        } else if (authentication != null && authentication.getPrincipal() instanceof SocialUser) {
            SocialUser model = (SocialUser) authentication.getPrincipal();
            UserVo userVo=new UserVo(model.getUserId(),model.getUsername());
            userVo.setMenus(menus);
            return userVo;
        }
        return null;
    }

}
