package com.example.springboot.service;

import com.example.springboot.dao.PermissionDao;
import com.example.springboot.dao.StudentDao;
import com.example.springboot.dao.TeacherDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.Permission;
import com.example.springboot.entity.SysStudent;
import com.example.springboot.entity.SysTeacher;
import com.example.springboot.entity.SysUser;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    public SysUser findByUserName(String name){
        return userDao.findByUserName(name);
    }

    public  void  delete(Integer id){

        userDao.deleteByPrimaryKey(id);
        studentDao.deleteByPrimaryKey(id);
    }
    //删除教师用户
    public  void  deletes(Integer id){

        userDao.deleteByPrimaryKey(id);
        teacherDao.deleteByPrimaryKey(id);
    }

    public void save(SysUser user){
        userDao.insert(user);

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUsers = userDao.findByUserName(principal.getUsername());
        SysStudent st=new SysStudent();
        st.setStuName(user.getRealname());
        st.setUsername(user.getUsername());
        st.setPassword(user.getPassword());
        st.setTeacherId(sysUsers.getId());
        st.setId(userDao.selectOne(user).getId());
        studentDao.insert(st);
    }

    //保存教师用户
    public void saves(SysUser user){
        userDao.insert(user);

        SysTeacher str=new SysTeacher();
        str.setId(user.getId());
        str.setName(user.getRealname());
        str.setPassword(user.getPassword());
        str.setUsername(user.getUsername());
        teacherDao.insert(str);
    }

    public int size(Integer s){
        SysStudent user=new SysStudent();
        user.setTeacherId(s);
        return studentDao.selectCount(user);
    }


    public List<SysStudent> listUsers(int page,int limit){
        PageHelper.startPage(page,limit);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUsers = userDao.findByUserName(principal.getUsername());
        sysUsers.getId();
        SysStudent sss=new SysStudent();
        sss.setTeacherId(sysUsers.getId());

        //SysUser user=new SysUser();
        //userDao.select(user);
        return studentDao.select(sss);
    }

    public List<SysTeacher> listTeachers(int page, int limit){
        PageHelper.startPage(page,limit);
        SysTeacher sss=new SysTeacher();
        return teacherDao.select(sss);
    }

    public int sizes(){
        SysTeacher user=new SysTeacher();
        return teacherDao.selectCount(user);
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
