package com.okay.qa.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-09:20:03
 * Modify date: 2018-04-09:20:03
 */
public final class SpringTool {

    public Object getBean(String beanId){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/application*.xml");
        return ac.getBean(beanId);
    }
}