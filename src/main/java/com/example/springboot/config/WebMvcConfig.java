package com.example.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//web视图管理
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/addUser").setViewName("addUser");
        registry.addViewController("/updateUser").setViewName("updateUser");
        registry.addViewController("/information").setViewName("information");
        registry.addViewController("/stuInformation").setViewName("stuInformation");
        registry.addViewController("/teaInformation").setViewName("teaInformation");
        registry.addViewController("/xueyuanTeaInformation").setViewName("xueyuanTeaInformation");
        registry.addViewController("/addTeacher").setViewName("addTeacher");
    }
}
