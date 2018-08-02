package com.okay.qa.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: liuzhanhui
 * @Decription: 日期处理工具
 * @Date: Created in 2018-04-09:20:03
 * Modify date: 2018-04-09:20:03
 */
public class DateTools {

    /**
     * 格式化日期yyyy-MM-dd-mm-ss
     * @param date
     * @return
     */
    public static String dateFormatMinute(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd-mm-ss");
        return simple.format(date);
    }
    /**
     * 格式化日期yyyy-MM-dd
     * @param date
     * @return
     */
    public static String dateFormatDay(Date date){
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
        return simple.format(date);
    }
}
