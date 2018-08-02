package com.okay.qa.pages;

import com.okay.qa.common.AppiumDriverWait;
import com.okay.qa.common.ExpectedCondition;
import com.okay.qa.domain.ConfigEntity;
import com.okay.qa.domain.ElementEntity;
import com.okay.qa.domain.PageEntity;
import com.okay.qa.utils.ResourceUrlUtils;
import com.okay.qa.utils.SpringTool;
import com.okay.qa.utils.YamlUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Set;

/**
 * @Author: liuzhanhui
 * @Decription:页面基本操作
 * @Date: Created in 2018-04-09:16:09
 * Modify date: 2018-04-09:16:09
 */
public class BasePage {

    protected Logger logger = Logger.getLogger(BasePage.class);
    public static AndroidDriver<MobileElement> androidDriver;
    protected String pagePath;

    /**
     * 初始化子类的元素文件位置(反射匹配文件名+类名)
     */
    public BasePage(){
        String pack[] = this.getClass().getCanonicalName().split("\\.");
        String packageName = pack[pack.length-2];
        String className = pack[pack.length-1];
        pagePath = ResourceUrlUtils.getClassPath()+"data/"+packageName+"/"+className+".yaml";
    }
    /**
     * 根据名称匹配元素实体
     * @param name
     * @return
     */
    protected ElementEntity getElementEntity(String name) {
        PageEntity pageEntity = YamlUtils.getPageEntity(pagePath);
        List<ElementEntity> elementEntities = pageEntity.getElement();
        for (ElementEntity elementEntity : elementEntities) {
            if (name.equals(elementEntity.getName())) {
                logger.info("获取页面：" + pageEntity.getPage() + "：author--》" + pageEntity.getAuthor() + ":创建时间--》" + pageEntity.getDate() + "：获取元素--》" + elementEntity.getName());
                return elementEntity;
            }
        }
        return null;
    }
    /**
     * 点击元素
     * @param clickElementBean
     */
    protected void click(ElementEntity clickElementBean){
        waitAppiumMoboleElement(clickElementBean).click();
        logger.info("点击元素成功："+clickElementBean.getName());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 清除
     * @param elementEntity
     */
    protected void clear(ElementEntity elementEntity){
        waitAppiumMoboleElement(elementEntity).clear();
        logger.info("清除元素成功："+elementEntity.getName());
    }
    /**
     * 双击元素
     * @param elementEntity
     */
    protected void doubleClick(ElementEntity elementEntity){
        getTouch().tap(waitAppiumMoboleElement(elementEntity)).waitAction(1000).perform();
        logger.info("双击元素："+elementEntity.getName());
    }

    /**
     * 元素输入值
     * @param elementEntity
     * @param charSequences
     */
    protected void sendkeys(ElementEntity elementEntity, CharSequence... charSequences){
        waitAppiumMoboleElement(elementEntity).sendKeys(charSequences);
        logger.info("输入的元素："+elementEntity.getName()+"，输入值："+charSequences);
    }

    /**
     * 元素设置值
     * @param elementEntity
     * @param value
     */
    protected void setValue(ElementEntity elementEntity, String value){
         waitAppiumMoboleElement(elementEntity).setValue(value);
        logger.info("输入的元素："+elementEntity.getName()+"，输入值："+value);
    }

    /**
     * 获取元素值
     * @param elementEntity
     * @return
     */
    protected String getText(ElementEntity elementEntity){
        logger.info("获取文本值的元素："+elementEntity.getName()+"获取文本值："+waitAppiumMoboleElement(elementEntity).getText());
        return waitAppiumMoboleElement(elementEntity).getText();
    }
    /**
     * 判断元素是否出现
     * @param elementEntity
     * @return
     */
    protected boolean isPrentElement(ElementEntity elementEntity){
        try {
            androidDriver.findElement(getBy(elementEntity));
            logger.info("查找元素："+elementEntity.getName()+"存在");
            return true;
        }catch(NoSuchElementException e){
            logger.info("查找元素："+elementEntity.getName()+"不存在");
            return false;
        }
    }
    /**
     * 获取手势操作
     * @return
     */
    private TouchAction getTouch(){
        TouchAction action = new TouchAction(androidDriver);
        return action;
    }
    /**
     * 获得by值
     * @param elementEntity
     * @return
     */
    private By getBy(ElementEntity elementEntity){
        By by = null;
        switch (elementEntity.getType()){
            case ID:
                by = By.id(elementEntity.getLocator());
                break;
            case NAME:
                by = By.name(elementEntity.getName());
                break;
            case CLASS:
                by = By.className(elementEntity.getLocator());
                break;
            case XPATH:
                by = By.xpath(elementEntity.getLocator());
                break;
            case LINKTEXT:
                by = By.linkText(elementEntity.getLocator());
                break;
             default:
                 by = By.xpath(elementEntity.getLocator());

        }
        return by;
    }
    /**
     * 超时等到元素出现
     * @param elementEntity
     * @return
     */
    protected MobileElement waitAppiumMoboleElement(final ElementEntity elementEntity){
        MobileElement mobileElement = null;
        SpringTool tool = new SpringTool();
        ConfigEntity configEntity = (ConfigEntity) tool.getBean("configSource");
        try {
            AppiumDriverWait wait = new AppiumDriverWait(androidDriver, configEntity.getWaitTimeOut());
            mobileElement = wait.until(new ExpectedCondition<MobileElement>() {

                public MobileElement apply(AppiumDriver<MobileElement> mobileElementAppiumDriver) {
                    return mobileElementAppiumDriver.findElement(getBy(elementEntity));
                }
            });
            logger.info("执行的元素：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());

        }catch (TimeoutException e){
            logger.error("查找元素超时：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());
        }catch (NoSuchElementException e){
            logger.error("元素未找到：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());

        }
        return mobileElement;
    }

    /**
     * 超时等到元素集合出现
     * @param elementEntity
     * @return
     */
    protected List<MobileElement> waitAppiumMoboleElements(final ElementEntity elementEntity){
        List<MobileElement> mobileElement = null;
        SpringTool tool = new SpringTool();
        ConfigEntity configEntity = (ConfigEntity) tool.getBean("configSource");
        try {
            AppiumDriverWait wait = new AppiumDriverWait(androidDriver, configEntity.getWaitTimeOut());
            mobileElement = wait.until(new ExpectedCondition<List<MobileElement>>() {
                public List<MobileElement> apply(AppiumDriver<MobileElement> mobileElementAppiumDriver) {
                    return mobileElementAppiumDriver.findElements(getBy(elementEntity));
                }
            });
            logger.info("执行的元素：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());

        }catch (TimeoutException e){
            logger.error("查找元素超时：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());
        }catch (NoSuchElementException e){
            logger.error("元素未找到：元素名称-->"+elementEntity.getName()+"，超时时间-->"+configEntity.getWaitTimeOut()+"，定位方式-->"+elementEntity.getType()+"，元素值-->"+elementEntity.getLocator()+"，元素描述-->"+elementEntity.getDescription());

        }
        return mobileElement;
    }
    /**
     * 页面往上滑动
     */
    protected void pageSwipeUp(){
        int height = androidDriver.manage().window().getSize().getHeight();
        int width = androidDriver.manage().window().getSize().getWidth();
        androidDriver.swipe(width/2, height*3/4, width/2, height/4, 1000);
        logger.info("页面往上滑动");
    }
    /**
     * 页面往下滑动
     */
    protected void pageSwipeDown(){
        int height = androidDriver.manage().window().getSize().getHeight();
        int width = androidDriver.manage().window().getSize().getWidth();
        androidDriver.swipe(width/2, height/4, width/2, height*3/4, 3000);
        logger.info("页面往下滑动");
    }
    /**
     * 页面往左滑动
     */
    protected void pageSwipeLeft(){
        int height = androidDriver.manage().window().getSize().getHeight();
        int width = androidDriver.manage().window().getSize().getWidth();
        androidDriver.swipe(width*3/4, height/2, width/4, height/2, 3000);
        logger.info("页面往左滑动");
    }
    /**
     * 页面往右滑动
     */
    protected void pageSwipeRight(){
        int height = androidDriver.manage().window().getSize().getHeight();
        int width = androidDriver.manage().window().getSize().getWidth();
        androidDriver.swipe(width/4, height/2, width/4, height*3/4, 3000);
        logger.info("页面往右滑动");
    }

    /**
     * 元素往上滑动
     * @param elementEntity
     */
    protected void elementSwipeUp(ElementEntity elementEntity){
        int height = waitAppiumMoboleElement(elementEntity).getSize().getHeight();
        int width = waitAppiumMoboleElement(elementEntity).getSize().getWidth();
        getTouch().press(waitAppiumMoboleElement(elementEntity), width/2, height*3/4).waitAction(1000).moveTo(waitAppiumMoboleElement(elementEntity),width/2,height/4).release().perform();
        logger.info("元素"+elementEntity.getName()+"往上滑动：");
    }

    /**
     * 元素往下滑动
     * @param elementEntity
     */
    protected void elementSwipeDown(ElementEntity elementEntity){

        int height = waitAppiumMoboleElement(elementEntity).getSize().getHeight();
        int width = waitAppiumMoboleElement(elementEntity).getSize().getWidth();
        getTouch().press(waitAppiumMoboleElement(elementEntity), width/2, height/4).waitAction(1000).moveTo(waitAppiumMoboleElement(elementEntity),width/2,height*3/4).release().perform();
        logger.info("元素"+elementEntity.getName()+"往下滑动：");
    }

    /**
     * 元素往左滑动
     * @param elementEntity
     */
    protected void elementSwipeLeft(ElementEntity elementEntity){
        int height = waitAppiumMoboleElement(elementEntity).getSize().getHeight();
        int width = waitAppiumMoboleElement(elementEntity).getSize().getWidth();
        getTouch().press(waitAppiumMoboleElement(elementEntity), width*3/4, height/2).waitAction(1000).moveTo(waitAppiumMoboleElement(elementEntity),width/4,height/2).release().perform();
        logger.info("元素"+elementEntity.getName()+"往左滑动：");
    }

    /**
     * 元素往右滑动
     * @param elementEntity
     */
    protected void elementSwipeRight(ElementEntity elementEntity){
        int height = waitAppiumMoboleElement(elementEntity).getSize().getHeight();
        int width = waitAppiumMoboleElement(elementEntity).getSize().getWidth();
        getTouch().press(waitAppiumMoboleElement(elementEntity), width/4, height/2).waitAction(1000).moveTo(waitAppiumMoboleElement(elementEntity),width*3/4,height/2).release().perform();
        logger.info("元素"+elementEntity.getName()+"往右滑动：");
    }

    /**
     * 往左滑动直到elementBean出现
     * @param elementEntity
     */
    protected void swipeLeftUtilElementPrent(ElementEntity elementEntity){
        int i = 0;
        while (!isPrentElement(elementEntity)){
            pageSwipeLeft();
            i++;
            if(i>10){
                logger.error("需等待出现的元素："+elementEntity.getName()+"未出现");
                return;
            }
        }
    }
    /**
     * 往有滑动直到elementEntity出现
     * @param elementEntity
     */
    protected void swipeRightUtilElementPrent(ElementEntity elementEntity){
        int i = 0;
        while (!isPrentElement(elementEntity)){
            pageSwipeRight();
            i++;
            if(i>10){
                logger.error("需等待出现的元素："+elementEntity.getName()+"未出现");
                return;
            }
        }
    }
    /**
     * 往上滑动直到elementEntity出现
     * @param elementEntity
     */
    protected void swipeUpUtilElementPrent(ElementEntity elementEntity){
        int i = 0;
        while (!isPrentElement(elementEntity)){
            pageSwipeUp();
            i++;
            if(i>10){
                logger.error("需等待出现的元素："+elementEntity.getName()+"未出现");
                return;
            }
        }
    }
    /**
     * 往下滑动直到elementEntity出现
     * @param elementEntity
     */
    protected void swipeDownUtilElementPrent(ElementEntity elementEntity){
        int i = 0;
        while (!isPrentElement(elementEntity)){
            pageSwipeDown();
            i++;
            if(i>10){
                logger.error("需等待出现的元素："+elementEntity.getName()+"未出现");
                return;
            }
        }
    }
    /**
     * 是否存在webview
     * @return
     */
    public boolean isExistWebview(){
        Set<String> context = androidDriver.getContextHandles();
        if(context.contains("WEBVIEW")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 切换到webview
     */
    public void switchContextToBrowser(){
        if(isExistWebview()){
            logger.info("切换到webview");
            androidDriver.context("WEBVIEW");
        }else{
            logger.error("不存在webview");
        }
    }

    /**
     * 切换到应用
     */
    public void switchContextToNativeApp(){
        logger.info("切换到应用");
        androidDriver.context("NATIVE_APP");
    }

    /**
     * 判断toast
     * @param toast
     * @return
     */
    protected boolean isPrentToast(String toast) {
        try {
            final WebDriverWait wait = new WebDriverWait(androidDriver, 10);
            Assert.assertNotNull(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[contains(@text,'" + toast + "')]"))));
            logger.info("查找toast成功！");
            return true;
        } catch (Exception e) {
            throw new AssertionError("找不到" + toast);
        }
    }
    public static void main(String[] args) {

    }
}
