package com.example.springboot.entity;

public class Response {
    private boolean success;
    private String filename;
    private String url;

    public Response(boolean success, String filename, String url) {
        this.success = success;
        this.filename = filename;
        this.url = url;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
