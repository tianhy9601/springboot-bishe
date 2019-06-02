package com.example.springboot.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

//用户表（sys_user表）
@Table(name = "Sys_user")
public class SysUser {
    //主键id

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;
    //用户名
    private String username;
    //登录密码
    private String password;

    private String realname;

    public Integer getIsTeacher() {
        return isTeacher;
    }

    public void setIsTeacher(Integer isTeacher) {
        this.isTeacher = isTeacher;
    }

    @Column(name = "is_teacher")
    private Integer isTeacher;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    private List<SysRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }
}
