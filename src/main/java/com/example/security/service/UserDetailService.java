package com.example.security.service;

import com.example.security.dao.UserConnectionDao;
import com.example.security.dao.UserDao;
import com.example.security.domain.dto.sys.UserTo;
import com.example.security.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService, SocialUserDetailsService {

//    @Autowired
//    private UserDao userDao;
//    @Autowired
//    private UserConnectionDao userConnectionDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        UserEntity user = userDao.queryTopByName(username);
//        if (user == null)
//            throw new UsernameNotFoundException("账户不存在");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ab"));
        UserTo userTo = new UserTo();
        userTo.setId(1);
        userTo.setName("张三");
        userTo.setPassword(new BCryptPasswordEncoder().encode("123"));
        userTo.setAuthorities(grantedAuthorities);
        return userTo;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        SocialUser socialUser = new SocialUser("abc", new BCryptPasswordEncoder().encode("123"), grantedAuthorities);
        return socialUser;
    }
}
