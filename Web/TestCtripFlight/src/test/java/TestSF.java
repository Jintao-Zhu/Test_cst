import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.K;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;



public class TestSF {
    private WebDriver driver;
    
    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.sf-express.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

     // test-code-start

    // 请在此处插入Selenium+JUnit5代码

    @ParameterizedTest
    @CsvSource({
            "广州市,黄埔区,黄埔东苑,南京市,鼓楼区,南京大学,SF_R001_001.png",
            "南京市,鼓楼区,南京大学,广州市,黄埔区,黄埔东苑,SF_R001_002.png",
    })
    public void test_SF_R001(String departure, String departure1, String departure2, String destination, String destination1, String destination2, String screenshotName) throws InterruptedException {
        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【运费时效】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[1]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div/div/div[1]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[2]/input")).sendKeys(departure);
        Thread.sleep(1000);
        // 选择对应城市的地区
        driver.findElement(By.xpath("//div[text()[contains(., '" + departure1 + "')]]")).click();
        // 输入对应的详细地址
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div/div/div[2]/figure/input")).sendKeys(departure2);

        // 打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div/div/div[1]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div[2]/div[2]/input")).sendKeys(destination);
        Thread.sleep(1000);
        // 选择对应城市的地区
        driver.findElement(By.xpath("//div[text()[contains(., '" + destination1 + "')]]")).click();
        // 在目的地的详细地址输入框中输入对应的详细地址
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div/div/div[2]/figure/input")).sendKeys(destination2);

        // 在重量输入框中输入“5”，在体积的长输入框中输入“20”，宽输入框中输入“15”，高输入框中输入“25”,在寄件时间控件中选择日期“16”
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).sendKeys("5");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[1]/input")).sendKeys("20");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[2]/input")).sendKeys("15");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[3]/input")).sendKeys("25");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[5]/figure/div/div[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[3]/table[1]/tbody/tr[5]/td[1]")).click();
        // 确定
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/button[2]")).click();
        Thread.sleep(1000);
        // 点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(3000);

        // 截图
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "香港,九龙城区,南京市,鼓楼区,SF_R002_001.png",
    })
    public void test_SF_R002(String departure, String departure1, String destination, String destination1, String screenshotName) throws InterruptedException {
        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【运费时效】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[1]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div/div/div[1]")).click();
        // gangaotai
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[1]/ul/li[2]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[6]/span")).click();
        Thread.sleep(1000);

        // 打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div/div/div[1]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div[2]/div[2]/input")).sendKeys(destination);
        Thread.sleep(1000);
        // 选择对应城市的地区
        driver.findElement(By.xpath("//div[text()[contains(., '" + destination1 + "')]]")).click();
        // 在重量输入框中输入“5”，在体积的长输入框中输入“20”，宽输入框中输入“15”，高输入框中输入“25”,在寄件时间控件中选择日期“16”
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).sendKeys("5");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[1]/input")).sendKeys("20");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[2]/input")).sendKeys("15");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[3]/input")).sendKeys("25");
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[5]/figure/div/div[2]")).click();
        Thread.sleep(1000);
        // 此刻
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/button[1]")).click();
        // 确定
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/button[2]")).click();
        Thread.sleep(1000);
        // 点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(3000);

        // 截图
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "香港,九龙城区,南京市,鼓楼区,SF_R003_001.png",
    })
    public void test_SF_R003(String departure, String departure1, String destination, String destination1, String screenshotName) throws InterruptedException {
        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【运费时效】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[1]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div/div/div[1]")).click();
        // gangaotai
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[1]/ul/li[2]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[6]/span")).click();
        Thread.sleep(1000);

        // 打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div/div/div[1]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div[2]/div[2]/input")).sendKeys(destination);
        Thread.sleep(1000);
        // 选择对应城市的地区
        driver.findElement(By.xpath("//div[text()[contains(., '" + destination1 + "')]]")).click();
        // 在重量输入框中输入“100”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).sendKeys("100");
        // 日期
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[5]/figure/div/div[2]")).click();
        Thread.sleep(1000);
        // 11-17 15:00
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[1]/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[1]/div/input")).sendKeys("2025-11-17");
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[2]/div[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[2]/div[1]/input")).sendKeys("15:00:00");
        // 确定
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/button[2]")).click();
        Thread.sleep(1000);
        // 点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(3000);

        // 截图
        takeScreenshot(screenshotName);
    }

    // 重量：19、20、19、20
    //长：5、5、100、100
    //宽：8、8、80、80
    //高：6、6、90、90
    @ParameterizedTest
    @CsvSource({
            "南京市,鼓楼区,19,5,8,6,SF_R004_001.png",
            "南京市,鼓楼区,20,5,8,6,SF_R004_002.png",
            "南京市,鼓楼区,19,100,80,90,SF_R004_003.png",
            "南京市,鼓楼区,20,100,80,90,SF_R004_004.png",
    })
    public void test_SF_R004(String destination, String destination1, String zhong, String chang, String kuan, String gao, String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【运费时效】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[1]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div/div/div[1]")).click();
        // gangaotai
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[1]/ul/li[2]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[1]/figure/div[2]/div[3]/div[2]/ul/li[6]/span")).click();
        Thread.sleep(1000);

        // 打开选择框窗口（默认为内地选项）
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div/div/div[1]")).click();
        // 输入对应城市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[2]/figure/div[2]/div[2]/input")).sendKeys(destination);
        Thread.sleep(1000);
        // 选择对应城市的地区
        driver.findElement(By.xpath("//div[text()[contains(., '" + destination1 + "')]]")).click();
        // 在重量输入框中输入“100”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[3]/figure/div/input")).sendKeys(zhong);

        // changkuangao
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[1]/input")).sendKeys(chang);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[2]/input")).sendKeys(kuan);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[4]/figure/div/div[3]/input")).sendKeys(gao);

        // 日期
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/ul/li[5]/figure/div/div[2]")).click();
        Thread.sleep(1000);
        // 11-17 15:00
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[1]/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[1]/div/input")).sendKeys("2025-11-18");
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[2]/div[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[7]/div[1]/div/div[1]/span[2]/div[1]/input")).sendKeys("08:00:00");
        // 确定
        driver.findElement(By.xpath("/html/body/div[7]/div[2]/button[2]")).click();
        Thread.sleep(1000);
        // 点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(3000);

        // 点击【大件（20kg+）】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/ul[2]/li[2]")).click();
        Thread.sleep(2000);

        // 截图
        takeScreenshot(screenshotName);
    }

    @Test
    public void test_SF_R005() throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【运费时效】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[1]")).click();

        // 【保价】、【代收贷款】、【签单返还】、【包装服务】
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/a[1]")).click();
//        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        takeScreenshot("SF_R005_001.png");

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/a[2]")).click();
//        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        takeScreenshot("SF_R005_002.png");

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/a[3]")).click();
//        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        takeScreenshot("SF_R005_003.png");

        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/a[4]")).click();
//        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        takeScreenshot("SF_R005_004.png");
    }

    @ParameterizedTest
    @CsvSource({
            "SF_R006_001.png",
    })
    public void test_SF_R006(String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【服务网点】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[2]")).click();

        // 点击【选择收寄件区域】选择框
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/input")).click();
        Thread.sleep(1000);
        // 上海市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[2]/span")).click();
        Thread.sleep(1000);
        // 黄浦区
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[1]/span")).click();
        Thread.sleep(1000);
        // 输入对应的详细地址
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/input")).sendKeys("兴业太古汇");
        driver.findElement(By.xpath("//*[contains(text(), '兴业太古汇')]")).click();
        Thread.sleep(1000);
        // 点击【搜索】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[3]/button")).click();
        Thread.sleep(3000);
        // 国泰君安快递服务中心
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[2]/span[7]")).click();
        Thread.sleep(2000);
        WebElement slider = driver.findElement(By.cssSelector(".BMap_stdMpSliderBar"));
        // 获取当前 top 值
        String topStr = slider.getCssValue("top");    // e.g. "12px"
        int currentTop = Integer.parseInt(topStr.replace("px", ""));
        // 目标位置
        int targetTop = 43;
        int offset = targetTop - currentTop;   // 需拖动的 Y 偏移量
        // 拖动
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider)
                .moveByOffset(0, offset)
                .release()
                .perform();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "广州市,天河区,天河城,SF_R007_001.png",
            "深圳市,福田区,天虹商场,SF_R007_002.png",
            "南京市,玄武区,新世界百货,SF_R007_003.png",
            "杭州市,西湖区,西城广场,SF_R007_004.png",
    })
    public void test_SF_R007(String departureCity, String departureCity1, String departureCity2, String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【服务网点】进入运费时效页面
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[1]/ul/li[3]/ul/li[2]")).click();

        // 点击【选择收寄件区域】选择框
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/input")).click();
        Thread.sleep(1000);

        // 广州市（选择天河区）、深圳市（选择福田区）、南京市（选择玄武区）、杭州市（选择西湖区）
        // 关键词：天河城（对应网点为正佳广场店）、天虹商场（对应网点为耀华楼店）、新世界百货（对应网点为洪武北路店）、西城广场（对应网点为电信大楼店）
        // 上海市
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys(departureCity);
        Thread.sleep(1000);
        // 天河区
        driver.findElement(By.xpath("//div[text()[contains(., '" + departureCity1 + "')]]")).click();
        Thread.sleep(1000);
        // 输入对应的详细地址
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/input")).sendKeys(departureCity2);
        driver.findElement(By.xpath("//*[contains(text(), '" + departureCity2 + "')]")).click();
        Thread.sleep(1000);
        // 点击【搜索】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[3]/button")).click();
        Thread.sleep(8000);

        // 国泰君安快递服务中心
//        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[2]/span[7]")).click();
//        Thread.sleep(2000);
        WebElement slider = driver.findElement(By.cssSelector(".BMap_stdMpSliderBar"));
        // 获取当前 top 值
        String topStr = slider.getCssValue("top");    // e.g. "12px"
        int currentTop = Integer.parseInt(topStr.replace("px", ""));
        // 目标位置
        int targetTop = 1;
        int offset = targetTop - currentTop;   // 需拖动的 Y 偏移量
        // 拖动
        Actions actions = new Actions(driver);
        actions.clickAndHold(slider)
                .moveByOffset(0, offset)
                .release()
                .perform();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }

    // 电子（选择电子琴）、电脑（选择笔记本电脑）
    @ParameterizedTest
    @CsvSource({
            "电子,电子琴,SF_R008_001.png",
            "电脑,笔记本电脑,SF_R008_002.png",
    })
    public void test_SF_R008(String box, String boxname, String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【收寄标准】进入收寄标准页面
        driver.findElement(By.xpath("//*[@id=\"chn\"]/div/div[1]/ul/li[3]/ul/li[3]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/input")).click();
        // 点击【省/直辖市】后，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[1]/ul/li[2]")).click();
        // 按顺序选择“广东省”、“深圳市”、“光明区”。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[19]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[3]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[10]/span")).click();
        Thread.sleep(1000);

        // 点击目的地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/input")).click();
        // 点击【省/直辖市】后，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[1]/ul/li[2]")).click();
        // 点击【省/直辖市】后，按顺序选择“江苏省”、“南京市”、“鼓楼区”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[10]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[1]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[4]/span")).click();
        Thread.sleep(1000);

        // 在托寄物品输入框中输入托寄物品名称，选择对应的物品。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[3]/input")).sendKeys(box);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(text(), '" + boxname + "')]")).click();
        Thread.sleep(2000);

        // 完成选择及输入操作后，用户点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/button")).click();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }

    // “猫咪”、“硫酸”、“现金”、“古董”。
    @ParameterizedTest
    @CsvSource({
            "猫咪,SF_R009_001.png",
            "硫酸,SF_R009_002.png",
            "现金,SF_R009_003.png",
            "古董,SF_R009_004.png",
    })
    public void test_SF_R009(String box, String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【收寄标准】进入收寄标准页面
        driver.findElement(By.xpath("//*[@id=\"chn\"]/div/div[1]/ul/li[3]/ul/li[3]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/input")).click();
        // 点击热门城市的【深圳市】，选择“南山区”。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[4]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[5]/span")).click();
        Thread.sleep(1000);

        // 点击目的地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/input")).click();
        // 点击【港澳台】，按顺序选择“香港”、“湾仔区”、“铜锣湾”
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[1]/ul/li[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[2]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[15]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[2]/span")).click();
        Thread.sleep(1000);

        // 在寄件人身份选择框中选择“个人”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[1]/div/div[2]/div[1]")).click();
        Thread.sleep(1000);
        // 在收件人身份选择框中选择“公司”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[2]/div[2]/div/div[2]/div[2]")).click();

        // 在托寄物品输入框中依次输入“猫咪”、“硫酸”、“现金”、“古董”。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[3]/input")).sendKeys(box);
        Thread.sleep(2000);

        // 完成选择及输入操作后，用户点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/button")).click();
        Thread.sleep(3000);

        // 截图
        takeScreenshot(screenshotName);
    }


    // 电子（选择电子琴）、电脑（选择笔记本电脑）
    @ParameterizedTest
    @CsvSource({
            "电子,电子琴,SF_R008_001.png",
            "电脑,笔记本电脑,SF_R008_002.png",
    })
    public void test_SF_R010(String box, String boxname, String screenshotName) throws InterruptedException {

        // xieyi
        driver.findElement(By.xpath("/html/body/div/section[1]/div/div/div[2]/div[2]")).click();
        // 点击【服务支持】按钮
        driver.findElement(By.xpath("/html/body/div/section[1]/section/div/div[2]/div[4]/div[1]/a")).click();
        // 点击左侧菜单栏的【收寄标准】进入收寄标准页面
        driver.findElement(By.xpath("//*[@id=\"chn\"]/div/div[1]/ul/li[3]/ul/li[3]")).click();
        // 点击始发地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/input")).click();
        // 点击【省/直辖市】后，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[1]/ul/li[2]")).click();
        // 按顺序选择“广东省”、“深圳市”、“光明区”。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[19]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[3]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[1]/div/div[3]/div[2]/ul/li[10]/span")).click();
        Thread.sleep(1000);

        // 点击目的地选择框，打开选择框窗口（默认为内地选项），
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/input")).click();
        // 点击【省/直辖市】后，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[1]/ul/li[2]")).click();
        // 点击【省/直辖市】后，按顺序选择“江苏省”、“南京市”、“鼓楼区”，
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[10]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[1]/span")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[1]/div[2]/div/div[3]/div[2]/ul/li[4]/span")).click();
        Thread.sleep(1000);

        // 在托寄物品输入框中输入托寄物品名称，选择对应的物品。
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/div[3]/input")).sendKeys(box);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[contains(text(), '" + boxname + "')]")).click();
        Thread.sleep(2000);

        // 完成选择及输入操作后，用户点击【查询】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[3]/div/div[2]/div/div[2]/button")).click();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }



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