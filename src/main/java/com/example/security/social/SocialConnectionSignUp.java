package com.example.security.social;

import com.example.security.dao.UserConnectionDao;
import com.example.security.dao.UserDao;
import com.example.security.domain.entity.UserConnectionEntity;
import com.example.security.domain.entity.UserEntity;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class SocialConnectionSignUp implements ConnectionSignUp {
    @Autowired
    UserConnectionDao userConnectionDao;
    @Autowired
    UserDao userDao;
    @Override
    public String execute(Connection<?> connection) {
        UserEntity user=new UserEntity();
        user.setName(connection.getDisplayName());
        user.setStatus(0);
        user.setPassword("123");
        UserEntity save=userDao.save(user);
        return String.valueOf(save.getId());
    }
}
