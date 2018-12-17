package com.example.security.controller;

import com.example.security.domain.dto.sys.UserTo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    ObjectMapper objectMapper;
    @GetMapping
    public String getCurrentUser() throws JsonProcessingException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserTo) {
            UserTo model = (UserTo) authentication.getPrincipal();
           return objectMapper.writeValueAsString(Optional.of(model.getUsername()));
        }
        else if (authentication != null && authentication.getPrincipal() instanceof SocialUser) {
            SocialUser model = (SocialUser) authentication.getPrincipal();
            return objectMapper.writeValueAsString(Optional.of(model.getUsername()));
        }
        return "";
    }
}
