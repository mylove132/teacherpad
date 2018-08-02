package com.okay.qa.usecase;

import com.okay.qa.pages.BasePage;
import com.okay.qa.system.AndroidDriverFactory;
import com.okay.qa.utils.ResourceUrlUtils;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

/**
 * @Author: liuzhanhui
 * @Decription: 初始化配置文件以及服务
 * @Date: Created in 2018-04-10:14:47
 * Modify date: 2018-04-10:14:47
 */
@ContextConfiguration(locations={"classpath:spring/application*.xml"})
public class BaseCase extends AbstractTestNGSpringContextTests {

    @BeforeTest
    public void initAndroidDriver(){
        PropertyConfigurator.configure(ResourceUrlUtils.getRootPath()+"/src/main/resources/log4j.properties");
        AndroidDriverFactory androidDriverFactory = new AndroidDriverFactory();
        AndroidDriver driver = androidDriverFactory.initAndroidDriver("127.0.0.1","4723");
        BasePage.androidDriver = driver;
    }

    @AfterTest(alwaysRun = true)
    public void quit() throws IOException {
        Runtime.getRuntime().exec("adb shell ime set com.android.inputmethod.latin/.LatinIME");
        BasePage.androidDriver.quit();
    }


}
