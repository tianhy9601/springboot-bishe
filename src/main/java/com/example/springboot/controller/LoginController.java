package com.example.springboot.controller;

import com.example.springboot.entity.Message;
import com.example.springboot.entity.SysUser;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(Model model){
        Message msg =  new Message("测试标题","测试内容","额外信息，只对管理员显示");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("msg", msg);
        SysUser user = userService.findByUserName(auth.getName());
        if(user.getIsTeacher()!=null && user.getIsTeacher().equals(1))
            return "index";//如果是后台管理人员登录
        else if (user.getIsTeacher()!=null && user.getIsTeacher().equals(2))
            return "addTeacher";
        else
            return "user";//如果是客户登录
//        return "index";
    }
    @RequestMapping("/admin")
    @ResponseBody
    public String hello(){
        return "hello admin";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public String getList(){
        return "hello getList";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public String save(){
        return "hello save";
    }


    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public String update(){
        return "hello update";
    }
}
