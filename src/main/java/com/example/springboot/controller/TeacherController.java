package com.example.springboot.controller;

import com.example.springboot.dao.StudentDao;
import com.example.springboot.dao.TeacherDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.*;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserService userService;
    @Autowired
    UserDao userDao;
    @Autowired
    TeacherDao teacherDao;

    @GetMapping
    @ResponseBody
    public LayuiResonse<SysTeacher> getUserss(@RequestParam(required = false, defaultValue = "1") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer limit){
        SysTeacher s=new SysTeacher();
        List<SysTeacher>  teachers =  userService.listTeachers(page,limit);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int size = userService.sizes();
        LayuiResonse<SysTeacher> resonse = new LayuiResonse<>("0","",size,teachers);
        return  resonse;

    }

    @PostMapping
    @ResponseBody
    public Response save(@RequestBody SysUser user){
        user.setIsTeacher(1);
        userService.saves(user);
        return new Response(true,"","");
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public Response deletes(@PathVariable("id") Integer id){
        userService.deletes(id);
        return new Response(true,"","");
    }

}

