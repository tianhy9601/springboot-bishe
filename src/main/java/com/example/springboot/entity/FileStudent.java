package com.example.springboot.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "File_student")
public class FileStudent {


    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    private String fileUrl;

    private String fileName;

    private Integer  stuId;

    private Integer teacherId;

    private String stuName;

    private String fileClass;

    public String getFileClass() {
        return fileClass;
    }

    public void setFileClass(String fileClass) {
        this.fileClass = fileClass;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getPass() {
        return pass;
    }

    public void setPass(Integer pass) {
        this.pass = pass;
    }

    private Integer pass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
}
