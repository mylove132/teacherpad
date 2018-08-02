package com.okay.qa.usecase.business;
import com.okay.qa.service.Impl.business.LoginSceneImpl;
import com.okay.qa.usecase.BaseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-10:14:45
 * Modify date: 2018-04-10:14:45
 */
@Service
public class LoginTest extends BaseCase {

    @Autowired
    private LoginSceneImpl loginSceneImpl;

    @Test
    public void Test1(){
        loginSceneImpl.login();
    }
}
