package com.example.springboot.service;

import com.example.springboot.dao.PermissionDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Permission;
import com.example.springboot.entity.SysUser;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserDao userDao;
    @Autowired
    PermissionDao permissionDao;

    public  void  delete(Integer id){
        userDao.deleteByPrimaryKey(id);
    }

    public void save(SysUser user){
        userDao.insert(user);
    }

    public int size(){
        SysUser user=new SysUser();
        return userDao.selectCount(user);
    }


    public List<SysUser> listUsers(int page,int limit){
        PageHelper.startPage(page,limit);
        SysUser user=new SysUser();
        return userDao.select(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        SysUser user = userDao.findByUserName(username);
        if (user != null) {
            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName()!=null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }
}
