package com.example.springboot.controller;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.UserPasswordUpdate;
import com.example.springboot.entity.LayuiResonse;
import com.example.springboot.entity.Response;
import com.example.springboot.entity.SysUser;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;
    @Autowired
    UserDao userDao;

    @GetMapping
    @ResponseBody
    public LayuiResonse<SysUser> getUsers(@RequestParam(required = false, defaultValue = "1") Integer page,
                                  @RequestParam(required = false, defaultValue = "10") Integer limit){
        SysUser s=new SysUser();
        List<SysUser>  users =  userService.listUsers(page,limit);
        int size = userService.size();
        LayuiResonse<SysUser> resonse = new LayuiResonse<>("0","",size,users);
        return  resonse;

    }

    @PostMapping
    @ResponseBody
    public Response save(@RequestBody SysUser user){
        userService.save(user);
        return new Response(true,"","");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Response delete(@PathVariable("id") Integer id){
        userService.delete(id);
        return new Response(true,"","");
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<Boolean> update(@RequestBody UserPasswordUpdate userPasswordUpdate) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser principal = userDao.findByUserName(userDetails.getUsername());
        if (principal.getPassword().equals(userPasswordUpdate.getOldPassword())) {
            SysUser user = new SysUser();
            user.setId(principal.getId());
            user.setPassword(userPasswordUpdate.getUpdatePassword());
            userDao.updateByPrimaryKeySelective(user);
            return new ResponseEntity<>(true, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

    @ResponseBody
    @GetMapping("/selectOldPassword")
    public SysUser selects(){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        return sysUser;
    }
}

