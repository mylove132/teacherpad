package com.okay.qa.service.business;

import com.okay.qa.pages.business.AttendClass;

/**
 * @Author: liuzhanhui
 * @Decription: 教师首页课程接口
 * @Date: Created in 2018-04-11:19:38
 * Modify date: 2018-04-11:19:38
 */
public interface AttendClassScene {

    /**
     * 教师上课
     */
    public void teacherAttendClass();

    /**
     * 教师备课
     */
    public void teacherLessonClass();

    /**
     * 教师批改
     */
    public void teacherCorrecting();
}
