package com.appium.test;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class ToastTest {

    AndroidDriver driver;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("platformName", "android");  // 测试平台
        cap.setCapability("deviceName", "127.0.0.1:21503"); // 测试机udid
        cap.setCapability("platforVersion", "5.1.1"); // 测试平台系统版本
        cap.setCapability("automationName", "uiautomator2");  // 自动化引擎解决输入问题   可填uiautomator2
        cap.setCapability("noReset", true); // 不清除app缓存数据
        // cap.setCapability("resetKeyboard","True"); // 隐藏键盘
        cap.setCapability(CapabilityType.BROWSER_NAME, "");
        // 设置包名
        cap.setCapability("appPackage", "com.douban.frodo");
        cap.setCapability("appActivity", "com.douban.frodo.activity.SplashActivity"); // 豆瓣
        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"), cap);  // 将配置传到appium服务端并连接手机或者虚拟机
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  // 隐式等待30秒全局，找到元素就继续执行下面程序，最长30秒内

    }

    @Test
    public void case1(){
//        driver.findElementByXPath("//android.widget.TextView[contains(@text,'我的')]").click();
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"我的\")").click();
        driver.findElementById("com.douban.frodo:id/unlongin_name").click();
        driver.findElementById("com.douban.frodo:id/input_password").sendKeys("12345");
        driver.findElementById("com.douban.frodo:id/input_user_name").sendKeys("13666666666");
        driver.findElementById("com.douban.frodo:id/sign_in_douban").click();
        // 获取toast值来进行断言
//        WebElement toastElement = driver.findElementByXPath("//*[contains(@text,'正在登陆')]");
        WebElement toastElement1 = driver.findElementByXPath("//*[contains(@text,'用户名和密码不匹配')]");
        System.out.println(toastElement1.getText());
        assertEquals(toastElement1.getText(),"用户和密码不匹配");

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
