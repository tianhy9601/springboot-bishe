package com.example.springboot.dao;

import com.example.springboot.entity.Permission;

import java.util.List;

public interface PermissionDao {
    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}