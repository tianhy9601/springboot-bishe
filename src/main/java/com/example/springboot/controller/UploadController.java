package com.example.springboot.controller;

import com.example.springboot.dao.FileStudentDao;
import com.example.springboot.dao.StudentDao;
import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.FileStudent;
import com.example.springboot.entity.Response;
import com.example.springboot.entity.SysStudent;
import com.example.springboot.entity.SysUser;
import com.example.springboot.service.UploadFile;
import com.example.springboot.service.UserService;
import com.example.springboot.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
public class UploadController {

    @Autowired
    private UploadFile upLoad;

    @Autowired
    private UserDao userDao;

    @Autowired
    StudentDao studentDao;

    @Autowired
    FileStudentDao fileSload;

    //处理文件上传
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Response> uploadImg(@RequestParam("fileClass") String fileClass,@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String prefix=fileName.substring(fileName.lastIndexOf(".")+1);
        fileName = UUID.randomUUID().toString();
        //获取根目录
        String rootPath = ResourceUtils.getURL("classpath:").getPath();
        String filePath = rootPath + "static/upload/";
        //上传成功路径
        String transferFileName;
        //上传
        FileUtil.uploadFile(file.getBytes(), filePath, fileName+"."+prefix);
        FileStudent fileStudent = new FileStudent();
        fileStudent.setFileClass(fileClass);
        if(fileSload.select(fileStudent).size()!=0){
            return ResponseEntity.ok().body(new Response(false,file.getOriginalFilename(),null));
        }
        fileStudent.setFileName(file.getOriginalFilename());
        fileStudent.setFileUrl(filePath+fileName+"."+prefix);
        fileStudent.setPass(0);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int ss=sysUser.getId();
        SysStudent tea=studentDao.selectByPrimaryKey(ss);
        int tt=tea.getTeacherId();
        fileStudent.setTeacherId(tt);
        fileStudent.setStuId(sysUser.getId());
        fileStudent.setStuName(sysUser.getRealname());
        upLoad.save(fileStudent);
        //返回文件的存放路径
        return ResponseEntity.ok().body(new Response(true,file.getOriginalFilename(),null));
    }
}
