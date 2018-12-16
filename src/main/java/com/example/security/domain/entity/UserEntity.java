package com.example.security.domain.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Getter
@Setter
@Table(name = "t_user")
public class UserEntity {

    @Id
    private int id;
    private String name;
    private String password;
    private String email;
    private String mobile;
    private int status;
    private String avatar;
    private String description;

}
