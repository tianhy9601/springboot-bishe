package com.example.springboot.service.impl;

import com.example.springboot.dao.FileStudentDao;
import com.example.springboot.entity.FileStudent;
import com.example.springboot.service.DownloadFile;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DownloadFileImpl implements DownloadFile {
    @Autowired
    FileStudentDao fileDown;


    @Override
    public List<FileStudent> download(Integer stuIds) {
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        return fileDown.select(fileStudent);
    }

    @Override
    public List<FileStudent> download(Integer stuIds, int page, int limit) {
        PageHelper.startPage(page,limit);
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        return fileDown.select(fileStudent);
    }

    @Override
    public List<FileStudent> downloads(int page, int limit) {
        PageHelper.startPage(page,limit);
        FileStudent fileStudent=new FileStudent();
        return fileDown.select(fileStudent);
    }

    @Override
    public int count(Integer stuIds) {
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        return fileDown.selectCount(fileStudent);
    }
    //教师
    public int counts(FileStudent fileStudents) {
        FileStudent fileStudent=new FileStudent();
        return fileDown.selectCount(fileStudent);
    }

    @Override
    public FileStudent getById(Integer id) {
        FileStudent fileStudent = new FileStudent();
        fileStudent.setId(id);
        return fileDown.selectOne(fileStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        FileStudent fileStudent=new FileStudent();
        fileStudent.setId(id);
        fileDown.deleteByPrimaryKey(fileStudent);
    }

    @Override
    @Transactional
    public void updatess(Integer id) {
        FileStudent fileStudent=fileDown.selectByPrimaryKey(id);
        fileStudent.setPass(1);
        fileDown.updateByPrimaryKey(fileStudent);
    }

    @Override
    @Transactional
    public FileStudent update(FileStudent fileStudents) {
         fileDown.updateByPrimaryKey(fileStudents);
        return fileStudents;
    }
}
