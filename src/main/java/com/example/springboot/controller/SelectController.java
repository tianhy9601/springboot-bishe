package com.example.springboot.controller;

import com.example.springboot.dao.UserDao;
import com.example.springboot.entity.FileStudent;
import com.example.springboot.entity.LayuiResonse;
import com.example.springboot.entity.SysUser;
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

    @ResponseBody
    @GetMapping("/select")
    public LayuiResonse<FileStudent> download(@RequestParam(required = false, defaultValue = "1") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer limit) {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser sysUser = userDao.findByUserName(principal.getUsername());
        int s=sysUser.getId();
//        String stuIds=sysUser.getId();
        List<FileStudent>  students =  downloadFiles.download(s,page,limit);
        int size = downloadFiles.count(s);
        LayuiResonse<FileStudent> resonse = new LayuiResonse<FileStudent>("0","",size,students);
        return  resonse;
    }

    @ResponseBody
    @GetMapping("/selects")
    public LayuiResonse<FileStudent> downloads(@RequestParam(required = false, defaultValue = "1") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer limit) {
        FileStudent s=new FileStudent();
//        String stuIds=sysUser.getId();
        List<FileStudent>  students =  downloadFiles.downloads(page,limit);
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
