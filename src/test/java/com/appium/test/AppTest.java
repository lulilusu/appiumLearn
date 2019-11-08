package com.appium.test;


//import io.appium.java_client.TouchAction;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.touch.WaitOptions;
//import io.appium.java_client.touch.offset.PointOption;

import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    AppiumDriver driver;

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
//        cap.setCapability("appPackage", "com.handsgo.jiakao.android");
//        cap.setCapability("appActivity", "com.handsgo.jiakao.android.splash.Login");
        cap.setCapability("appPackage", "com.baidu.BaiduMap");
        cap.setCapability("appActivity", "com.baidu.baidumaps.WelcomeScreen");
        driver = new AndroidDriver(new URL("http:127.0.0.1:4723/wd/hub"), cap);  // 将配置传到appium服务端并连接手机或者虚拟机
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  // 隐式等待30秒全局，找到元素就继续执行下面程序，最长30秒内

    }

    @Test(description = "单点触控", enabled = false) // 如下拉刷新
    public void Refresh() throws InterruptedException {
        Thread.sleep(10000);
            // 这里就相当抄写java-client 4.1.2版本doswipe方法
        {
            TouchAction tTouchAction = new TouchAction(driver);
            PointOption startPoint = PointOption.point(356, 594);  // 开始坐标
            PointOption endPoint = PointOption.point(356, 794);// 移动到坐标
//        Duration duration = Duration.ofMillis(8000);
            WaitOptions waitOptions = WaitOptions.waitOptions(Duration.ofMillis(8000));  // 时间类型转换8秒
            tTouchAction.press(startPoint).waitAction(waitOptions).moveTo(endPoint).release().perform(); // moveTo 移动到，release释放
//            tTouchAction.perform(); // 执行
        }

        // java-client 4.1.2版本api
//        driver.swipe(356,594,356,794, 8000);

    }

    @Test(description = "多次滑动",enabled = false) // 如手势解锁
    public void multiSwipe(){
        TouchAction tTouchAction = new TouchAction(driver);
        PointOption point1 = PointOption.point(111, 3333);
        PointOption point2 = PointOption.point(111, 3333);
        PointOption point3 = PointOption.point(111, 3333);
        PointOption point4 = PointOption.point(111, 3333);
        tTouchAction.press(point1).moveTo(point2).moveTo(point3).moveTo(point4).release().perform();

    }

    @Test(description = "多点触摸") // 比如用在地图、图片的放大或者缩小
    public void multiTouch(){
        MultiTouchAction multiTouchAction = new MultiTouchAction(driver);  // 实例化多点触控对象

        TouchAction touchAction1 = new TouchAction(driver);  // 实例加两个TouchAction表示两根手指
        TouchAction touchAction2 = new TouchAction(driver);

        // 获取屏幕高度和宽度
        int x = driver.manage().window().getSize().getWidth();
        int y = driver.manage().window().getSize().getHeight();

        // 第一个手指
        touchAction1.press(PointOption.point(x*2/10, y*2/10)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x*4/10, y*4/10)).release();
        // 第二个手指
        touchAction2.press(PointOption.point(x*8/10, y*8/10)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).moveTo(PointOption.point(x*6/10, y*6/10)).release();

        // 加入到多点触控对象中并执行
        multiTouchAction.add(touchAction1).add(touchAction2).perform();



    }


    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
