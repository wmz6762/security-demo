package com.example.security.dao;

import com.example.security.domain.entity.UserConnectionEntity;
import com.example.security.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserConnectionDao extends JpaRepository<UserConnectionEntity,String> {
}
