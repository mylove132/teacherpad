package com.okay.qa.system;

import com.okay.qa.domain.ConfigEntity;
import com.okay.qa.utils.SpringTool;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-10:16:10
 * Modify date: 2018-04-10:16:10
 */
public class AndroidDriverFactory {

    private Logger logger = Logger.getLogger(AndroidDriverFactory.class);
    private static AndroidDriver driver;

    public AndroidDriver<MobileElement> initAndroidDriver(String ip, String port){
        URL url = null;
        try {
            url = new URL("http://"+ip+":"+port+"/wd/hub");
        } catch (MalformedURLException e) {
            logger.error("启动URL报错");
            e.printStackTrace();
        }
        try {
            driver = new AndroidDriver<MobileElement>(url, getCapabilities());
            logger.info("服务地址："+ip+"服务端口："+port+"启动成功");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return driver;
    }

    private DesiredCapabilities getCapabilities(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        SpringTool tool = new SpringTool();
        ConfigEntity configEntity = (ConfigEntity) tool.getBean("configSource");
        capabilities.setCapability("platformName",configEntity.getPlatformName());
        capabilities.setCapability("deviceName", configEntity.getDeviceName());
        //设置android系统版本
        capabilities.setCapability("platformVersion",configEntity.getPlatformVersion());
        capabilities.setCapability("unicodeKeyboard",configEntity.getUnicodeKeyboard());
        //重置输入法为系统默认
        capabilities.setCapability("resetKeyboard",configEntity.getResetKeyboard());
        //no need sign 安装时不对apk进行重签名，设置很有必要，否则有的apk在重签名之后无法正常使用
        capabilities.setCapability("noSign",configEntity.getNoSign());
        //设置app的主包名和主类名
        capabilities.setCapability("appPackage",configEntity.getAppPackage());
        capabilities.setCapability("appActivity",configEntity.getAppActivity());
        capabilities.setCapability("automationName", "UiAutomator2");
        return capabilities;
    }
}
