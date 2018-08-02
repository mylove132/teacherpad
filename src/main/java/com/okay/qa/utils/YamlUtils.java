package com.okay.qa.utils;

import com.okay.qa.domain.ConfigEntity;
import com.okay.qa.domain.PageEntity;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


/**
 * @Author: liuzhanhui
 * @Decription: yaml文件解析工具
 * @Date: Created in 2018-04-09:20:03
 * Modify date: 2018-04-09:20:03
 */
public class YamlUtils {

    static private Logger logger = Logger.getLogger(YamlUtils.class);

    public static PageEntity getPageEntity(String pagePath){
        Yaml yaml = new Yaml();
        InputStream in = null;
        try {
            in = new FileInputStream(new File(pagePath));
        } catch (FileNotFoundException e) {
            logger.error("加载元素页面失败："+pagePath);
            e.printStackTrace();
        }
        PageEntity page = yaml.loadAs(in, PageEntity.class);
        return page;
    }

}
