import javafx.scene.control.Tab;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TestCtrip {
    private WebDriver driver;


    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ctrip.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    // test-code-start

    // 请在此处插入JUnit5代码

    @ParameterizedTest
    @CsvSource({
            "北京,广州,2024-12-20,Ctrip_R001_001.png,无",
            "北京,成都,2024-12-20,Ctrip_R001_002.png,无",
            "上海,广州,2024-12-20,Ctrip_R001_003.png,无",
            "上海,成都,2024-12-20,Ctrip_R001_004.png,无"
    })
    public void test_Ctrip_R001(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from+ Keys.TAB);
        Thread.sleep(5000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to+Keys.TAB);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "123,Ctrip_R002_001.png",
            "@,Ctrip_R002_002.png",
    })
    public void test_Ctrip_R002(String from, String screenShot) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }

    @Test
    public void test_Ctrip_R003() throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys("北京"+ Keys.TAB);
        Thread.sleep(5000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys("上海"+Keys.TAB);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/ul/li/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/ul/li[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[1]/div[1]/ul[2]/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[1]/div[1]/ul[2]/li[3]")).click();

        takeScreenshot("Ctrip_R003_001.png");
    }

    @ParameterizedTest
    @CsvSource({
            "南京,广州,2024-11-17,Ctrip_R004_001.png,无",
    })
    public void test_Ctrip_R004(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from+ Keys.TAB);
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to+Keys.TAB);
        Thread.sleep(1000);

//        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
//        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/ul/li/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[1]/ul/li/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[3]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[4]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[4]/ul/li[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[5]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[5]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[6]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[6]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[7]/ul/li[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div[2]/div[1]/div[2]/div[7]/ul/li[1]/strong")).click();

        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "济南,西安,2024-11-17,Ctrip_R005_001.png,无",
            "济南,杭州,2024-11-17,Ctrip_R005_002.png,无",
            "天津,西安,2024-11-17,Ctrip_R005_003.png,无",
            "天津,杭州,2024-11-17,Ctrip_R005_004.png,无"
    })
    public void test_Ctrip_R005(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[1]/div/ul/li[2]")).click();

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[1]/div[1]/strong")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[2]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[3]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div[3]/div/div[1]/div[2]/div[3]/ul/li[4]/div[1]")).click();


        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "兰州,郑州,2024-11-17,Ctrip_R006_001.png,无",
            "兰州,厦门,2024-11-17,Ctrip_R006_002.png,无",
            "徐州,郑州,2024-11-17,Ctrip_R006_003.png,无",
            "徐州,厦门,2024-11-17,Ctrip_R006_004.png,无"
    })
    public void test_Ctrip_R006(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "123,海南,2024-11-18,Ctrip_R007_001.png,无",
            "@,海南,2024-11-18,Ctrip_R007_002.png,无",
            "测试,海南,2024-11-18,Ctrip_R007_003.png,无",
    })
    public void test_Ctrip_R007(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "Ctrip_R008_001.png"
    })
    public void test_Ctrip_R008(String screenShot) throws InterruptedException {
        driver.manage().window().maximize();

        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "哈尔滨,济南,2024-11-17,Ctrip_R009_001.png,无",
            "哈尔滨,西安,2024-11-17,Ctrip_R009_002.png,无",
            "哈尔滨,拉萨,2024-11-17,Ctrip_R009_003.png,无",
            "长春,济南,2024-11-17,Ctrip_R009_004.png,无",
            "长春,西安,2024-11-17,Ctrip_R009_005.png,无",
            "长春,拉萨,2024-11-17,Ctrip_R009_006.png,无",
            "安吉,济南,2024-11-17,Ctrip_R009_007.png,无",
            "安吉,西安,2024-11-17,Ctrip_R009_008.png,无",
            "安吉,拉萨,2024-11-17,Ctrip_R009_009.png,无",

    })
    public void test_Ctrip_R009(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "安吉,拉萨,2024-11-17,Ctrip_R010_001.png,123",
            "安吉,拉萨,2024-11-17,Ctrip_R010_002.png,@",
            "安吉,拉萨,2024-11-17,Ctrip_R010_003.png,测试",
    })
    public void test_Ctrip_R010(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "安吉,拉萨,2024-11-18,Ctrip_R011_001.png,济南",
            "安吉,拉萨,2024-11-18,Ctrip_R011_002.png,安阳",
            "安吉,拉萨,2024-11-18,Ctrip_R011_003.png,周口",
    })
    public void test_Ctrip_R011(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div/div[2]/div/div[1]/div/div[3]/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[1]/input")).sendKeys(from);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).clear();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[3]/div/form[2]/input")).sendKeys(to);
        Thread.sleep(2000);

        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[4]/div[1]/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/div/div[2]/div[2]/ul[2]/li[20]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/div/div[2]/div[1]/div[2]/button")).click();
        Thread.sleep(2000);
        takeScreenshot(screenShot);
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
