package com.example.security.dao;

import com.example.security.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends JpaRepository<UserEntity,Integer> {

    UserEntity queryTopByName(String name);
}
