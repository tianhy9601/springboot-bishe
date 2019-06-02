package com.example.springboot.dao;

import com.example.springboot.entity.SysUser;
import tk.mybatis.mapper.common.BaseMapper;

public interface UserDao extends BaseMapper<SysUser> {
    SysUser findByUserName(String username);
}