import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestCtrip {
    private WebDriver driver;

    @BeforeEach
    public void setup() {
        // 提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ctrip.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    // test-code-start

    @ParameterizedTest
    @CsvSource({
            "北京,广州,Ctrip_R001_001.png",
            "北京,成都,Ctrip_R001_002.png",
            "上海,广州,Ctrip_R001_003.png",
            "上海,成都,Ctrip_R001_004.png"
    })
    public void test_Ctrip_R001(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(2000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to + Keys.TAB);
        Thread.sleep(2000);

        // 日期
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]/strong[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[26]")).click();
        Thread.sleep(1000);
//        driver.findElement(By.xpath(
//                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[3]/div[1]"))
//                .click();
//        Thread.sleep(200);
//        driver.findElements(
//                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).get(0)
//                .click();
//        Thread.sleep(200);
//        driver.findElement(By.xpath(
//                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]"))
//                .click();
//        Thread.sleep(500);

        // 高铁
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/i")).click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "无效,广州,Ctrip_R002_001.png",
            "无效,北京,Ctrip_R002_002.png"
    })
    public void test_Ctrip_R002(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(2000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[3]/div[1]"))
                .click();
        Thread.sleep(200);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "南京,广州,Ctrip_R003_001.png"
    })
    public void test_Ctrip_R003(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(2000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"))
                .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(500);

        // 筛选
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/ul/li/i"))
                .click();
        Thread.sleep(500);

        // 车型
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[1]/i")).click();
        Thread.sleep(500);

        // 出发时间
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/ul/li[2]"))
                .click();
        Thread.sleep(500);

        // 价格排序
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[1]/div[1]/ul[2]/li[3]/div"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[1]/div[1]/ul[2]/li[3]/div"))
                .click();

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "南京,广州,Ctrip_R004_001.png"
    })
    public void test_Ctrip_R004(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(2000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[3]/div[1]"))
                .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(500);

        // 筛选
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/ul/li/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[1]/i")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/ul/li[1]"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[4]/ul/li[1]"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[5]/ul/li[1]/i")).click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[6]/ul/li[1]/i")).click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[7]/ul/li[1]/i")).click();

        // 重置
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[4]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[5]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[6]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[7]/div/a"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[3]")).click();
        Thread.sleep(500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "济南,西安,Ctrip_R005_001.png",
            "济南,杭州,Ctrip_R005_002.png",
            "天津,西安,Ctrip_R005_003.png",
            "天津,杭州,Ctrip_R005_004.png"
    })
    public void test_Ctrip_R005(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[2]/i"))
                .click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[2]/div[1]"))
                .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[2]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[28]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(500);

        // 筛选
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/div"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[1]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[2]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[3]/div[2]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[4]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[5]/div[1]/i"))
                .click();

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "兰州,郑州,Ctrip_R006_001.png",
            "兰州,厦门,Ctrip_R006_002.png",
            "徐州,郑州,Ctrip_R006_003.png",
            "徐州,厦门,Ctrip_R006_004.png"
    })
    public void test_Ctrip_R006(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[2]/i"))
                .click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        if (to.equals("郑州"))
            driver.findElement(By.xpath(
                    "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"))
                    .click();
        else
            driver.findElement(By.xpath(
                    "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[2]/div[1]"))
                    .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[2]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[28]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(500);

        // 筛选
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/div"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[1]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[2]/div[2]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[3]/div[2]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[4]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[5]/div[1]/i"))
                .click();

        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "111,海口,Ctrip_R007_001.png",
            "&&,海口,Ctrip_R007_002.png",
            "不存在,海口,Ctrip_R007_003.png"
    })
    public void test_Ctrip_R007(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[2]")).click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[2]/div[1]"))
                .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[23]"))
                .click();
        Thread.sleep(500);

        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[2]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[28]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "Ctrip_R008_001.png"
    })
    public void test_Ctrip_R008(String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[2]/i"))
                .click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul[1]/li[5]"))
                .click();
        Thread.sleep(200);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul[6]/li[6]"))
                .click();
        Thread.sleep(1000);

        // 到达地
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul[1]/li[1]"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul[2]/li[1]"))
                .click();
        Thread.sleep(500);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[23]"))
                .click();
        Thread.sleep(500);

        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[2]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[25]"))
                .click();
        Thread.sleep(500);

        // 高铁动车
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[3]/i")).click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(500);

        // 筛选
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/ul[1]/li[3]"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[2]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[3]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[4]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/div"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[3]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[4]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[5]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[6]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/div"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[4]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[5]/div[1]/i"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[3]/div[2]/div[3]/ul/li[6]/div[1]/i"))
                .click();
        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "哈尔滨,济南,Ctrip_R009_001.png",
            "哈尔滨,西安,Ctrip_R009_002.png",
            "哈尔滨,拉萨,Ctrip_R009_003.png",
            "长春,济南,Ctrip_R009_004.png",
            "长春,西安,Ctrip_R009_005.png",
            "长春,拉萨,Ctrip_R009_006.png",
            "安吉,济南,Ctrip_R009_007.png",
            "安吉,西安,Ctrip_R009_008.png",
            "安吉,拉萨,Ctrip_R009_009.png"
    })
    public void test_Ctrip_R009(String from, String to, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[3]/i"))
                .click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        if (to.equals("西安"))
            driver.findElement(By.xpath(
                    "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[2]/div[1]"))
                    .click();
        else
            driver.findElement(By.xpath(
                    "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"))
                    .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "哈尔滨,济南,无效,Ctrip_R010_001.png",
            "哈尔滨,拉萨,111,Ctrip_R010_002.png",
            "哈尔滨,拉萨,&&,Ctrip_R010_003.png"
    })
    public void test_Ctrip_R010(String from, String to, String zhong, String screenShot) throws InterruptedException {
        // 火车票
        driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[3]/i"))
                .click();
        Thread.sleep(1000);

        // 出发地
        driver.findElement(By.id("label-departStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-departStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-departStation")).sendKeys(from);
        Thread.sleep(200);

        // 中转
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"))
                .click();
        Thread.sleep(500);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"))
                .clear();
        Thread.sleep(200);
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"))
                .sendKeys(zhong);
        Thread.sleep(200);

        // 到达地
        driver.findElement(By.id("label-arriveStation")).click();
        Thread.sleep(500);
        driver.findElement(By.id("label-arriveStation")).clear();
        Thread.sleep(200);
        driver.findElement(By.id("label-arriveStation")).sendKeys(to);
        Thread.sleep(2000);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"))
                .click();
        Thread.sleep(200);

        // 日期
        driver.findElement(
                By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath(
                "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[22]"))
                .click();
        Thread.sleep(500);

        // 搜索
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(1500);
        takeScreenshot(screenShot);
    }

    /*
     * @ParameterizedTest
     * 
     * @CsvSource({
     * "哈尔滨,南京,济南,Ctrip_R011_001.png",
     * "哈尔滨,南京,安阳,Ctrip_R011_002.png",
     * "哈尔滨,南京,周口,Ctrip_R0011_003.png"
     * })
     * public void test_Ctrip_R011(String from, String to, String zhong, String
     * screenShot) throws InterruptedException {
     * // 火车票
     * driver.findElements(By.className("lsn_top_nav_qdgwe")).get(3).click();
     * Thread.sleep(1000);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[3]/i"
     * )).click();
     * Thread.sleep(1000);
     * 
     * // 出发地
     * driver.findElement(By.id("label-departStation")).click();
     * Thread.sleep(500);
     * driver.findElement(By.id("label-departStation")).clear();
     * Thread.sleep(200);
     * driver.findElement(By.id("label-departStation")).sendKeys(from);
     * Thread.sleep(200);
     * 
     * // 到达地
     * driver.findElement(By.id("label-arriveStation")).click();
     * Thread.sleep(500);
     * driver.findElement(By.id("label-arriveStation")).clear();
     * Thread.sleep(200);
     * driver.findElement(By.id("label-arriveStation")).sendKeys(to);
     * Thread.sleep(2000);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"
     * )).click();
     * Thread.sleep(200);
     * 
     * // 日期
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]"
     * )).click();
     * Thread.sleep(200);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[1]/ul[2]/li[23]"
     * )).click();
     * Thread.sleep(500);
     * 
     * // 中转
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"
     * )).click();
     * Thread.sleep(500);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"
     * )).clear();
     * Thread.sleep(200);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[2]/form/input"
     * )).sendKeys(zhong);
     * Thread.sleep(1200);
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[2]/div/div[2]/ul/li[1]/div[1]"
     * )).sendKeys(zhong);
     * Thread.sleep(200);
     * 
     * 
     * // 搜索
     * driver.findElement(By.xpath(
     * "/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).
     * click();
     * Thread.sleep(1500);
     * takeScreenshot(screenShot);
     * }
     */
    // test-code-end

    @AfterEach
    public void teardown() {
        this.driver.quit();
    }

    private void takeScreenshot(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmssddSSS");
        String timestamp = dateFormat.format(new Date());
        String timestampedFileName = timestamp + "_" + fileName;
        File screenshotsDir = new File("screenshots");
        if (!screenshotsDir.exists()) {
            screenshotsDir.mkdirs();
        }
        String screenshotFilePath = screenshotsDir.getAbsolutePath() + File.separator + timestampedFileName;
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(screenshotFilePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
