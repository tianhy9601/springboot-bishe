package com.example.springboot.util;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author dengyouquan
 **/
public class FileUtil {

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String getDownloadPath() {
        //获取根目录
        String rootPath = null;
        try {
            rootPath = ResourceUtils.getURL("classpath:").getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String downloadPath = rootPath + "static/download/";
        File targetFile = new File(downloadPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        return downloadPath;
    }
}
