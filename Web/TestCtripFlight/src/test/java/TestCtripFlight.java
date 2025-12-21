import net.bytebuddy.asm.Advice;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class TestCtripFlight {
    private WebDriver driver;


    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        // 无头模式
//        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.ctrip.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
    // test-code-start

    // 请在此处插入Selenium+JUnit5代码
    @ParameterizedTest
    @CsvSource({
            "北京,广州,Ctripflight_R001_001.png",
            "北京,成都,Ctripflight_R001_002.png",
            "上海,广州,Ctripflight_R001_003.png",
            "上海,成都,Ctripflight_R001_004.png"
    })
    public void test_CtripFlight_R001(String departure, String destination, String screenshotName) throws InterruptedException {

        // 打开航班选择界面
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]")).click();

        // ============ 出发城市 ============
        WebElement cityInput = driver.findElement(By.name("owDCity"));
        cityInput.click();
        cityInput.sendKeys(Keys.CONTROL + "a");
        cityInput.sendKeys(Keys.DELETE);
        cityInput.sendKeys(departure);
        cityInput.sendKeys(Keys.TAB);

        // ============ 到达城市 ============
        WebElement cityArriveInput = driver.findElement(By.name("owACity"));
        cityArriveInput.click();
        cityArriveInput.sendKeys(Keys.CONTROL + "a");
        cityArriveInput.sendKeys(Keys.DELETE);
        cityArriveInput.sendKeys(destination);
        cityArriveInput.sendKeys(Keys.TAB);

        // ============ 日期选择（假设选择某个固定日期） ============
        WebElement date = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[2]/div/div[1]/span/div/div/div/input"));
        date.click();

        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]/div/div[2]/div[6]"));
        new Actions(driver).moveToElement(element).click().perform();

        // ============ 搜索航班 ============
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/div/div/div[2]/ul/li[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/button")).click();

        Thread.sleep(3000);

        // ============ 截图 ============
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "测试,Ctripflight_R002_001.png",
            "12345456,Ctripflight_R002_002.png",
    })
    public void test_CtripFlight_R002(String departure, String screenshotName) throws InterruptedException {
        // 打开航班选择界面
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]")).click();

        // ============ 出发城市 ============
        WebElement cityInput = driver.findElement(By.name("owDCity"));
        cityInput.click();
        cityInput.sendKeys(Keys.CONTROL + "a");
        cityInput.sendKeys(Keys.DELETE);
        cityInput.sendKeys(departure);
        Thread.sleep(3000);

        // ============ 截图 ============
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "北京,东京,Ctripflight_R003_001.png",
            "北京,大阪,Ctripflight_R003_002.png",
            "上海,东京,Ctripflight_R003_003.png",
            "上海,大阪,Ctripflight_R003_004.png"
    })
    public void test_CtripFlight_R003(String departure, String destination, String screenshotName) throws InterruptedException {

        // 打开航班选择界面
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]")).click();

        // ============ 出发城市 ============
        WebElement cityInput = driver.findElement(By.name("owDCity"));
        cityInput.click();
        cityInput.sendKeys(Keys.CONTROL + "a");
        cityInput.sendKeys(Keys.DELETE);
        cityInput.sendKeys(departure);
        cityInput.sendKeys(Keys.TAB);

        // ============ 到达城市 ============
        WebElement cityArriveInput = driver.findElement(By.name("owACity"));
        cityArriveInput.click();
        cityArriveInput.sendKeys(Keys.CONTROL + "a");
        cityArriveInput.sendKeys(Keys.DELETE);
        cityArriveInput.sendKeys(destination);
        cityArriveInput.sendKeys(Keys.TAB);

        // ============ 日期选择（假设选择某个固定日期） ============
        WebElement date = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[2]/div/div[1]/span/div/div/div/input"));
        date.click();

        WebElement element = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[1]/div[2]/div/div[2]/div[6]"));
        new Actions(driver).moveToElement(element).click().perform();

        // =========== 乘客类型 ===========
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[1]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[2]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div[2]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[3]/div[2]/div[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[4]/a[1]")).click();

        // ============ 搜索航班 ============
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/button")).click();

        Thread.sleep(3000);

        // ============ 截图 ============
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
