package com.okay.qa.pages.business;

import com.okay.qa.domain.ElementEntity;
import com.okay.qa.pages.BasePage;
import org.springframework.stereotype.Component;

/**
 * @Author: liuzhanhui
 * @Decription: 登录场景页面
 * @Date: Created in 2018-04-10:14:11
 * Modify date: 2018-04-10:14:11
 */

@Component
public class Login extends BasePage {

    public void setUserName(String userName){
        ElementEntity elementEntity = getElementEntity("LOGIN_USER_EDIT");
        clear(elementEntity);
        setValue(elementEntity, userName);
    }
    public void setPassword(String password){
        ElementEntity elementEntity = getElementEntity("LOGIN_PASSWORD_EDIT");
        clear(elementEntity);
        setValue(elementEntity, password);
    }
    public void clickLoginButton(){
        ElementEntity elementEntity = getElementEntity("LOGIN_LOGIN_BUTTON");
        click(elementEntity);
    }
    public void loginSuccessToastVerity(){
        isPrentToast("登录成功");
    }
}
