package com.example.springboot.service.impl;

import com.example.springboot.dao.FileStudentDao;
import com.example.springboot.entity.FileStudent;
import com.example.springboot.service.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadFileImpl implements UploadFile {
    @Autowired
    FileStudentDao fileSload;

    @Override
    public FileStudent getCityByCityIdWithProcessItems(Integer stuId){
        FileStudent fileStudent=new FileStudent();
        fileSload.insert(fileStudent);
        return null;
    }

    @Override
    public void save(FileStudent file) {
        fileSload.insert(file);
    }

}
