package com.okay.qa.common;

import com.google.common.base.Function;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

/**
 * @Author: liuzhanhui
 * @Decription: 改造webdriver的智能等待接口
 * @Date: Created in 2018-04-10:11:48
 * Modify date: 2018-04-10:11:48
 */
public interface ExpectedCondition<T> extends Function<AppiumDriver<MobileElement>, T> {}