package com.okay.qa.service.Impl.business;

import com.okay.qa.domain.ConfigEntity;
import com.okay.qa.pages.business.Login;
import com.okay.qa.service.business.LoginScene;
import com.okay.qa.utils.SpringTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-10:14:31
 * Modify date: 2018-04-10:14:31
 */
@Service
public class LoginSceneImpl implements LoginScene {

    @Autowired
    private Login login;

    @Autowired
    private ConfigEntity configEntity;

    public void login() {
        login.setUserName(configEntity.getUsername());
        login.setPassword(configEntity.getPassword());
        login.clickLoginButton();
        login.loginSuccessToastVerity();
    }
}
