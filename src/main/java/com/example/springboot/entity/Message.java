package com.example.springboot.entity;


//中间信息表（无数据库实体表，只用来专递中间信息）
public class Message {
    //信息标题
    private String title;
    //信息主体
    private String content;
    //额外信息
    private String etraInfo;

    public Message(String title, String content, String etraInfo) {
        super();
        this.title = title;
        this.content = content;
        this.etraInfo = etraInfo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getEtraInfo() {
        return etraInfo;
    }
    public void setEtraInfo(String etraInfo) {
        this.etraInfo = etraInfo;
    }
}
