package com.example.springboot.service;

import com.example.springboot.entity.FileStudent;
import java.util.List;

public interface DownloadFile {
    List<FileStudent> download(Integer stuIds);
    List<FileStudent> download(Integer stuIds,int page,int limit);
    List<FileStudent> downloads(int page,int limit);
    int count(Integer stuIds);
    void delete(Integer id);
    void updatess(Integer id);
    FileStudent update(FileStudent fileStudents);
    //教师
    int counts(FileStudent fileStudents);

    FileStudent getById(Integer id);
}
