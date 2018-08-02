package com.okay.qa.service.Impl.business;

import com.okay.qa.pages.business.AttendClass;
import com.okay.qa.service.business.AttendClassScene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-11:19:42
 * Modify date: 2018-04-11:19:42
 */
@Service
public class AttendClassImpl implements AttendClassScene {

    @Autowired
    private AttendClass attendClass;

    public void teacherAttendClass() {
        attendClass.clickAttendClassButton();
    }

    public void teacherLessonClass() {
        attendClass.clickLessonClassButton();
    }

    public void teacherCorrecting() {
        attendClass.clickCorrectingButton();
    }
}
