package com.example.springboot.service;

import com.example.springboot.entity.FileStudent;
import com.example.springboot.entity.SysStudent;
import com.example.springboot.entity.SysTeacher;

import java.util.List;

public interface DownloadFile {
    List<FileStudent> download(Integer stuIds);
    List<FileStudent> download(String stuName,Integer stuIds,int page,int limit);
    List<FileStudent> downloadsss(Integer teacherId,int page,int limit);
    List<FileStudent> downloads(int page,int limit);
    List<SysStudent> downloadStu(Integer teacherId, int page, int limit);
    int countStu(Integer teacherId);

    List<SysTeacher> downloadTeac( int page, int limit);
    int countTeac();

    List<SysStudent> downloadStus(Integer id, int page, int limit);

    List<SysTeacher> downloadTea(Integer teacherId, int page, int limit);
    int countTea(Integer teacherId);

    int countStus(Integer id);
    int count(Integer stuIds);
    void delete(Integer id);
    void updatess(Integer id);
    FileStudent update(FileStudent fileStudents);
    SysStudent updateStu(SysStudent sysStudent);
    SysTeacher updateTea(SysTeacher sysTeacher);
    //教师
    int counts(FileStudent fileStudents);

    FileStudent getById(Integer id);
}
