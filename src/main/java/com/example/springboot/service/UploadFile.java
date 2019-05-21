package com.example.springboot.service;

import com.example.springboot.entity.FileStudent;

public interface UploadFile {
    FileStudent getCityByCityIdWithProcessItems(Integer stuId);

    void save(FileStudent file);
}
