package com.example.springboot.controller;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.*;
import com.example.springboot.service.DownloadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class SelectController {
    @Autowired
    DownloadFile downloadFiles;
    @Autowired
    private UserDao userDao;


    //学生数据表格
    @ResponseBody
    @GetMapping("/select")
    public LayuiResonse<FileStudent> download(@RequestParam(required = false, defaultValue = "1") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int s=sysUser.getId();
//        String stuIds=sysUser.getId();
        String sssss=sysUser.getRealname();
        List<FileStudent>  students =  downloadFiles.download(sssss,s,page,limit);
        int size = downloadFiles.count(s);
        LayuiResonse<FileStudent> resonse = new LayuiResonse<FileStudent>("0","",size,students);
        return  resonse;
    }

    //教师看学生的信息界面
    @ResponseBody
    @GetMapping("/selectStu")
    public LayuiResonse<SysStudent> downloadStudent(@RequestParam(required = false, defaultValue = "1") Integer page,
                                             @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int s=sysUser.getId();
        List<SysStudent>  students =  downloadFiles.downloadStu(s,page,limit);
        int size = downloadFiles.countStu(s);
        LayuiResonse<SysStudent> resonse = new LayuiResonse<SysStudent>("0","",size,students);
        return  resonse;
    }

    //教师看学生的信息界面
    @ResponseBody
    @GetMapping("/selectTeaXue")
    public LayuiResonse<SysTeacher> downloadTeacherXue(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer limit) {

        List<SysTeacher>  teachers =  downloadFiles.downloadTeac(page,limit);
        int size = downloadFiles.countTeac();
        LayuiResonse<SysTeacher> resonse = new LayuiResonse<SysTeacher>("0","",size,teachers);
        return  resonse;
    }

    //学生的用户信息界面
    @ResponseBody
    @GetMapping("/selectStus")
    public LayuiResonse<SysStudent> downloadStudents(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int s=sysUser.getId();
        List<SysStudent>  students =  downloadFiles.downloadStus(s,page,limit);
        int size = downloadFiles.countStus(s);
        LayuiResonse<SysStudent> resonse = new LayuiResonse<SysStudent>("0","",size,students);
        return  resonse;
    }

    //教师的用户信息界面
    @ResponseBody
    @GetMapping("/selectTea")
    public LayuiResonse<SysTeacher> downloadTeacher(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                     @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int s=sysUser.getId();
        List<SysTeacher>  teachers =  downloadFiles.downloadTea(s,page,limit);
        int size = downloadFiles.countTea(s);
        LayuiResonse<SysTeacher> resonse = new LayuiResonse<SysTeacher>("0","",size,teachers);
        return  resonse;
    }



    //教师数据表格
    @ResponseBody
    @GetMapping("/selects")
    public LayuiResonse<FileStudent> downloads(@RequestParam(required = false, defaultValue = "1") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int ss=sysUser.getId();
        FileStudent s=new FileStudent();
        s.setTeacherId(sysUser.getId());
//        String stuIds=sysUser.getId();
        List<FileStudent>  students =  downloadFiles.downloadsss(ss,page,limit);
        int size = downloadFiles.counts(s);
        LayuiResonse<FileStudent> resonse = new LayuiResonse<FileStudent>("0","",size,students);
        return  resonse;
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Integer id) {
        downloadFiles.delete(id);
    }

    //修改
//    @ResponseBody
    @PutMapping("/edit/{id}")
    public FileStudent edits(@PathVariable(name = "id") Integer id, @RequestBody FileStudent ss) {
        ss.setId(id);
        return downloadFiles.update(ss);
    }

    @PutMapping("/editStu/{id}")
    public SysStudent editStu(@PathVariable(name = "id") Integer id, @RequestBody SysStudent ss) {
        ss.setId(id);
        return downloadFiles.updateStu(ss);
    }

    @PutMapping("/editTea/{id}")
    public SysTeacher editTea(@PathVariable(name = "id") Integer id, @RequestBody SysTeacher st) {
        st.setId(id);
        return downloadFiles.updateTea(st);
    }

    @GetMapping("/download/{id}")
    public String downloadPPT(@PathVariable(name = "id") Integer id,HttpServletResponse response) {
        FileStudent fileStudent =  downloadFiles.getById(id);
        String downloadPath = fileStudent.getFileUrl();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream;charset=utf-8");
        File file = new File(downloadPath);
        if (!file.exists()) {
            System.out.println("file not exists!");
            return "file not exits";
        }
        //response.setContentType("application/force-download");
        //获取文件后缀名
        String prefix=file.getName().substring(file.getName().lastIndexOf(".")+1);
        String fileName = null;
        try {
            fileName = URLEncoder.encode(fileStudent.getFileName()+"."+prefix, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            byte[] buff =new byte[1024];
            int n;
            while((n=in.read(buff))!=-1){
                outputStream.write(buff,0,n);
            }
            outputStream.flush();
            outputStream.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "file download success!";
    }
    @PutMapping("/defines/{id}")
    public void editss(@PathVariable(name = "id") Integer id) {
        downloadFiles.updatess(id);
    }


}
