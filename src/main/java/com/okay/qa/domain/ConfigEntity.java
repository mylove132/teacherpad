package com.okay.qa.domain;

import java.io.Serializable;

/**
 * @Author: liuzhanhui
 * @Decription:
 * @Date: Created in 2018-04-09:18:45
 * Modify date: 2018-04-09:18:45
 */
public class ConfigEntity implements Serializable {

    private int waitTimeOut;

    private String username;

    private String password;

    private String platformName;

    private String platformVersion;

    private String deviceName;

    private String appPackage;

    private String noSign;

    private String noReset;

    private String appActivity;

    private String unicodeKeyboard;

    private String resetKeyboard;


    public int getWaitTimeOut() {
        return waitTimeOut;
    }

    public void setWaitTimeOut(int waitTimeOut) {
        this.waitTimeOut = waitTimeOut;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getPlatformVersion() {
        return platformVersion;
    }

    public void setPlatformVersion(String platformVersion) {
        this.platformVersion = platformVersion;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAppPackage() {
        return appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getNoSign() {
        return noSign;
    }

    public void setNoSign(String noSign) {
        this.noSign = noSign;
    }

    public String getNoReset() {
        return noReset;
    }

    public void setNoReset(String noReset) {
        this.noReset = noReset;
    }

    public String getAppActivity() {
        return appActivity;
    }

    public void setAppActivity(String appActivity) {
        this.appActivity = appActivity;
    }

    public String getUnicodeKeyboard() {
        return unicodeKeyboard;
    }

    public void setUnicodeKeyboard(String unicodeKeyboard) {
        this.unicodeKeyboard = unicodeKeyboard;
    }

    public String getResetKeyboard() {
        return resetKeyboard;
    }

    public void setResetKeyboard(String resetKeyboard) {
        this.resetKeyboard = resetKeyboard;
    }
}
