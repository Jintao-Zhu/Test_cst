package org.example;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Test12306 {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        //换成你的驱动路径
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        //如果报403错误，请加上下面这三行代码.并注释掉第30行代码(driver = new ChromeDriver();)
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
//        driver = new ChromeDriver();
        driver.get("https://www.12306.cn/index/index.html");
        driver.manage().window().maximize();
        //方法已弃用
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //如果提示报错，请取消注释
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    @Test
    public void testUI() {
        takeScreenshot("12306_search_UI_001.png");
    }

    @ParameterizedTest
    @CsvSource({
            //时间分别为当前日期的前一天日期
            // 当前日期
            // 预售期内任意日期
            // 预售期最后一天日期
            // 超出预售期期限的后一天
            "北京北,南京,2024-10-14,12306_search_001.png",
            "北京北,南京,2024-10-15,12306_search_002.png",
            "北京北,南京,2024-10-20,12306_search_003.png",
            "北京北,南京,2024-10-29,12306_search_004.png",
            "北京北,南京,2024-10-30,12306_search_005.png"
    })
    public void test_12306_search(String from, String to, String fromTime, String screenShot) throws InterruptedException {
        WebElement fromField = driver.findElement(By.id("fromStationText"));
        fromField.clear();
        fromField.sendKeys(from + Keys.ENTER);

        WebElement toField = driver.findElement(By.id("toStationText"));
        toField.clear();
        toField.sendKeys(to + Keys.ENTER);

        WebElement fromTimeField = driver.findElement(By.id("train_date"));
        fromTimeField.clear();
        fromTimeField.sendKeys(fromTime);


        WebElement query = driver.findElement(By.id("search_one"));
        query.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }


    @ParameterizedTest
    @CsvSource({
            "北京北,南京,2024-10-14,12306_search_student_001.png",
            "北京北,南京,2024-10-15,12306_search_student_002.png",
            "北京北,南京,2024-10-20,12306_search_student_003.png",
            "北京北,南京,2024-10-29,12306_search_student_004.png",
            "北京北,南京,2024-10-30,12306_search_student_005.png"

    })

    public void testSearchStudent(String from, String to, String fromTime, String screenShot) throws InterruptedException {
        WebElement fromField = driver.findElement(By.id("fromStationText"));
        fromField.clear();
        fromField.sendKeys(from + Keys.ENTER);

        WebElement toField = driver.findElement(By.id("toStationText"));
        toField.clear();
        toField.sendKeys(to + Keys.ENTER);

        WebElement fromTimeField = driver.findElement(By.id("train_date"));
        fromTimeField.clear();
        fromTimeField.sendKeys(fromTime);


        WebElement query = driver.findElement(By.id("search_one"));
        query.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }


    @ParameterizedTest
    @CsvSource({
            "北京北,南京,2024-10-14,12306_search_gd_001.png",
            "北京北,南京,2024-10-15,12306_search_gd_002.png",
            "北京北,南京,2024-10-20,12306_search_gd_003.png",
            "北京北,南京,2024-10-29,12306_search_gd_004.png",
            "北京北,南京,2024-10-30,12306_search_gd_005.png"

    })
    public void testSearchGd(String from, String to, String fromTime, String screenShot) throws InterruptedException {
        WebElement fromField = driver.findElement(By.id("fromStationText"));
        fromField.clear();
        fromField.sendKeys(from + Keys.ENTER);

        WebElement toField = driver.findElement(By.id("toStationText"));
        toField.clear();
        toField.sendKeys(to + Keys.ENTER);

        WebElement fromTimeField = driver.findElement(By.id("train_date"));
        fromTimeField.clear();
        fromTimeField.sendKeys(fromTime);


        WebElement query = driver.findElement(By.id("search_one"));
        query.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "北京北,南京,2024-10-14,12306_search_student_gd_001.png",
            "北京北,南京,2024-10-15,12306_search_student_gd_002.png",
            "北京北,南京,2024-10-20,12306_search_student_gd_003.png",
            "北京北,南京,2024-10-29,12306_search_student_gd_004.png",
            "北京北,南京,2024-10-30,12306_search_student_gd_005.png"
    })

    public void testSearchStuGd(String from, String to, String fromTime, String screenShot) throws InterruptedException {
        WebElement fromField = driver.findElement(By.id("fromStationText"));
        fromField.clear();
        fromField.sendKeys(from + Keys.ENTER);

        WebElement toField = driver.findElement(By.id("toStationText"));
        toField.clear();
        toField.sendKeys(to + Keys.ENTER);

        WebElement fromTimeField = driver.findElement(By.id("train_date"));
        fromTimeField.clear();
        fromTimeField.sendKeys(fromTime);


        WebElement query = driver.findElement(By.id("search_one"));
        query.click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }




    @AfterEach
    public void teardown() {
        this.driver.quit();
    }


    //截图方法，保存到当前工作路径
    private void takeScreenshot(String fileName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

