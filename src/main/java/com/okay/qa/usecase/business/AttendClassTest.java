package com.okay.qa.usecase.business;

import com.okay.qa.service.Impl.business.AttendClassImpl;
import com.okay.qa.service.Impl.business.LoginSceneImpl;
import com.okay.qa.usecase.BaseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * @Author: liuzhanhui
 * @Decription: 上课用例
 * @Date: Created in 2018-04-12:14:12
 * Modify date: 2018-04-12:14:12
 */
public class AttendClassTest extends BaseCase {

    @Autowired
    private LoginSceneImpl loginSceneImpl;

    @Autowired
    private AttendClassImpl attendClassImpl;

    @Test
    public void test(){
        attendClassImpl.teacherAttendClass();
        loginSceneImpl.login();
    }
}
