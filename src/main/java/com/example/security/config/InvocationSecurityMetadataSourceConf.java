package com.example.security.config;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.HashMap;


public class InvocationSecurityMetadataSourceConf implements FilterInvocationSecurityMetadataSource {

    private HashMap<String, String[]> map = null;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private void loadResourceDefine() {
        map = new HashMap<>();
//        map.put("/",new String[]{"public"});
        map.put("/account/register", new String[]{"admin"});

    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (map == null) loadResourceDefine();
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        for(String url : map.keySet()){
            if (antPathMatcher.match(url, requestUrl))
                return SecurityConfig.createList(map.get(url));
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

