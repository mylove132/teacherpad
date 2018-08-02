package com.okay.qa.utils;

import java.io.File;
import java.io.InputStream;

/**
 * @Author: liuzhanhui
 * @Decription:资源url工具包
 * @Date: Created in 2018-04-10:10:48
 * Modify date: 2018-04-10:10:48
 */
public class ResourceUrlUtils {

    /**
     * 获取项目根路径
     * @return
     */
    public static String getRootPath(){
        File file = new File("");
        String path = file.getAbsolutePath();
        return path;
    }

    /**
     * 获取classpath路径(切割去除file:)
     * @return
     */
    public static String getClassPath(){
       String path = Class.class.getResource("/").getPath();
//     String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
//     path = path.split("file:")[1];
       return path;
    }
}
