package com.example.springboot.service.impl;

import com.example.springboot.dao.FileStudentDao;
import com.example.springboot.dao.StudentDao;
import com.example.springboot.dao.TeacherDao;
import com.example.springboot.entity.FileStudent;
import com.example.springboot.entity.SysStudent;
import com.example.springboot.entity.SysTeacher;
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
    @Autowired
    StudentDao studentDao;
    @Autowired
    TeacherDao teacherDao;

    @Override
    public List<FileStudent> download(Integer stuIds) {
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        return fileDown.select(fileStudent);
    }

    //学生数据表格
    @Override
    public List<FileStudent> download(String stuName,Integer stuIds, int page, int limit) {
        PageHelper.startPage(page,limit);
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        fileStudent.setStuName(stuName);
        return fileDown.select(fileStudent);
    }

    //教师数据表格
    @Override
    public List<FileStudent> downloadsss(Integer teacherId, int page, int limit) {
        PageHelper.startPage(page,limit);
        FileStudent fileStudent=new FileStudent();
        fileStudent.setTeacherId(teacherId);
        return fileDown.select(fileStudent);
    }

    @Override
    public List<FileStudent> downloads(int page, int limit) {
        PageHelper.startPage(page,limit);
        FileStudent fileStudent=new FileStudent();
        return fileDown.select(fileStudent);
    }

    @Override
    public List<SysStudent> downloadStu(Integer teacherId, int page, int limit) {
        SysStudent sysStudent=new SysStudent();
        sysStudent.setTeacherId(teacherId);
        return studentDao.select(sysStudent);
    }

    @Override
    public int countStu(Integer teacherId) {
        SysStudent sysStudent=new SysStudent();
        sysStudent.setTeacherId(teacherId);
        return studentDao.selectCount(sysStudent);
    }

    @Override
    public List<SysTeacher> downloadTeac(int page, int limit) {
        SysTeacher teacher=new SysTeacher();
        return teacherDao.select(teacher);
    }

    @Override
    public int countTeac() {
        SysTeacher teacher=new SysTeacher();
        return teacherDao.selectCount(teacher);
    }

    @Override
    public List<SysStudent> downloadStus(Integer id, int page, int limit) {
        SysStudent sysStudent=new SysStudent();
        sysStudent.setId(id);
        return studentDao.select(sysStudent);
    }

    @Override
    public List<SysTeacher> downloadTea(Integer teacherId, int page, int limit) {
        SysTeacher sysTeacher=new SysTeacher();
        sysTeacher.setId(teacherId);
        return teacherDao.select(sysTeacher);
    }

    @Override
    public int countTea(Integer teacherId) {
        SysTeacher sysTeacher=new SysTeacher();
        sysTeacher.setId(teacherId);
        return teacherDao.selectCount(sysTeacher);
    }

    @Override
    public int countStus(Integer id) {
        SysStudent sysStudent=new SysStudent();
        sysStudent.setId(id);
        return studentDao.selectCount(sysStudent);
    }

    @Override
    public int count(Integer stuIds) {
        FileStudent fileStudent=new FileStudent();
        fileStudent.setStuId(stuIds);
        return fileDown.selectCount(fileStudent);
    }
    //分页总数
    public int counts(FileStudent fileStudents) {
        //FileStudent fileStudent=new FileStudent();
        return fileDown.selectCount(fileStudents);
    }

    //下载
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

    //审核
    @Override
    @Transactional
    public void updatess(Integer id) {
        FileStudent fileStudent=fileDown.selectByPrimaryKey(id);
        fileStudent.setPass(1);
        fileDown.updateByPrimaryKey(fileStudent);
    }

    //编辑
    @Override
    @Transactional
    public FileStudent update(FileStudent fileStudents) {
         fileDown.updateByPrimaryKey(fileStudents);
        return fileStudents;
    }

    @Override
    @Transactional
    public SysStudent updateStu(SysStudent sysStudent) {
        studentDao.updateByPrimaryKey(sysStudent);
        return sysStudent;
    }

    @Override
    public SysTeacher updateTea(SysTeacher sysTeacher) {
        teacherDao.updateByPrimaryKey(sysTeacher);
        return sysTeacher;
    }
}
