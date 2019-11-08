package com.appium.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;


import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.KeyEvent;
import javafx.scene.input.KeyCode;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class App {

    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName","android");  // 测试平台
        cap.setCapability("deviceName","127.0.0.1:21503"); // 测试机udid
        cap.setCapability("platforVersion","5.1.1"); // 测试平台系统版本
        cap.setCapability("automationName", "uiautomator2");  // 自动化引擎解决输入问题   可填uiautomator2
        cap.setCapability("noReset","true"); // 不清除app缓存数据
       // cap.setCapability("resetKeyboard","True"); // 隐藏键盘
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
//        cap.setCapability(CapabilityType., "");

        // 设置包名
        cap.setCapability("appPackage","com.handsgo.jiakao.android");
        cap.setCapability("appActivity","com.handsgo.jiakao.android.splash.Login");

        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"),cap);  // 将配置传到appium服务端并连接手机或者虚拟机
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  // 隐式等待30秒全局，找到元素就继续执行下面程序，最长30秒内


    }

    @Test()
    public void plus() throws InterruptedException {

//        Thread.sleep(20000);  // 等待20秒


//        int width = driver.manage().window().getSize().width;
//        int height = driver.manage().window().getSize().height;
//        System.out.println("进来了");
        // xpath定位相对路径
//        driver.findElementByXPath("//android.widget.TextView[contains(@text,'热门')]").click();
//        Thread.sleep(5000);
//        driver.findElementById("tv.danmaku.bili:id/expand_search").sendKeys("appium");
//        driver.pressKeyCode(AndroidKeyCode.HOME);

        // 驾考宝典
        Thread.sleep(2000);
        driver.findElementById("com.handsgo.jiakao.android:id/select_text").click();
        // 选择地区
//        driver.findElementById("com.handsgo.jiakao.android:id/edt_search_q").sendKeys("重庆");
//        driver.findElementById("com.handsgo.jiakao.android:id/item_title").click();
        // 多个值下定位
        driver.findElementById("com.handsgo.jiakao.android:id/edt_search_q").sendKeys("长");
//        List<WebElement> elementsById = driver.findElementsById("com.handsgo.jiakao.android:id/item_title");  // 返回一个list 泛型WebElement
//        elementsById.get(2).click(); // 通过返回元素定位并点击

        // 使用文本值来定位地区
        driver.findElementByAndroidUIAutomator("new Uiselector().text(\"长沙\")"); // 该条执行失败还没有找到原因

        driver.findElementById("com.handsgo.jiakao.android:id/item_car").click();

        driver.findElementById("com.handsgo.jiakao.android:id/ok_button").click();
        // 第三步
        driver.findElementById("com.handsgo.jiakao.android:id/gender_male_btn").click();
        driver.findElementById("com.handsgo.jiakao.android:id/jiakao_not_sign_up_btn").click();
        driver.findElementById("com.handsgo.jiakao.android:id/naben_btn").click();
        driver.findElementById("com.handsgo.jiakao.android:id/btn_next").click();

        //断言,通过类名
        String expected = "cn.mucang.android.mars.student.refactor.business.inquiry.activity.InquiryActivity";   //adb shell dumpsys activity | find  "mFocusedActivity"
        String actual = driver.currentActivity(); // 获取前台活跃的包名
        Assert.assertEquals(actual,expected);

    }

    @Test(description = "accessibilityId定位")
    public void accessibilityId(){
        // 在uiautomatorviewer没有该属性，对应的是content-desc属性
        driver.findElementsByAccessibilityId("ttt");
        driver.findElementByClassName("");
    }

    @Test(description = "显式等待")
    public void timeout(){
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);  // 初始化等待时间10
        WebElement element = webDriverWait.until(new ExpectedCondition<WebElement>() {   // 自定义等待未找到报错

            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver input) {  // 重写apply方法
                return driver.findElementById("com.handsgo.jiakao.android:id/select_text");  // 返回定位元素
            }
        });
        element.click();  // 找到后元素操作点击

    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
