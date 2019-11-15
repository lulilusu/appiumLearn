package com.appium.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumApiTest {
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
        cap.setCapability("appPackage", "com.handsgo.jiakao.android");
        cap.setCapability("appActivity", "com.handsgo.jiakao.android.splash.Login");
//        cap.setCapability("appPackage", "com.baidu.BaiduMap");
//        cap.setCapability("appActivity", "com.baidu.baidumaps.WelcomeScreen");
        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"), cap);  // 将配置传到appium服务端并连接手机或者虚拟机
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  // 隐式等待30秒全局，找到元素就继续执行下面程序，最长30秒内

    }

    @Test(enabled = false)
    public void apiTest() throws InterruptedException {
        Thread.sleep(11000);
        // 1. app内部页面跳转  adb shell dumpsys activity | find "mFocusedActivity" 该命令获取当前app展示的页面路径
        Activity activity = new Activity("com.handsgo.jiakao.android","com.baojiazhijia.qichebaojia.lib.app.common.serial.SerialListBrandActivity");
        driver.startActivity(activity);  // 该方法实例化对象AndroidDriver才有

        // 2. app跳转到另一个app,传入另一个app的package、activity
        Activity activity2 = new Activity("com.baidu.BaiduMap","com.baidu.baidumaps.WelcomeScreen");
        driver.startActivity(activity2);

    }

    @Test(enabled = false)
    public void apiTest1() throws IOException {
        driver.getPageSource();  // 获取当前页面的dom结构
        driver.currentActivity(); // 获取当前页面的类名
        driver.resetApp();   // 清除app数据
        driver.getDeviceTime(); // 获取设备时间
        driver.getDisplayDensity();  // 获取设备DPI，屏幕密度
        driver.getAutomationName();  // 获取自动化引擎automaion name，默认null，uiautomator2便是引擎
        driver.getOrientation();    // 获取设备横屏竖屏状态
        driver.isAppInstalled("com.baidu.BaiduMap");  // 判断app是否安装传入包名

        //KeyEvent keyEvent = new KeyEvent(); // 键值操作  失败

    }


    @Test
    public void apiTest2() throws IOException {
        File file = driver.getScreenshotAs(OutputType.FILE);// 截图
        FileUtils.copyFile(file,new File("D:\\text.png"));

    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}
