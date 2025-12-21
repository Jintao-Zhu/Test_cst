import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.concurrent.TimeUnit;


public class TestCtripFlight2 {
    private WebDriver driver;


    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
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
            "北京,广州,test_Ctripflight_R001_001.png",
            "北京,成都,test_Ctripflight_R001_002.png",
            "上海,广州,test_Ctripflight_R001_003.png",
            "上海,成都,test_Ctripflight_R001_004.png"
    })
    public void test_Ctripflight_R001(String from, String to, String screenShot) throws InterruptedException {
        // 机票
        driver.findElement(By.xpath("//*[@id=\"leftSideNavLayer\"]/div/div/div[1]/div/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        Thread.sleep(1000);
        //单程
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]/span")).click();
        Thread.sleep(500);
        // 出发地
        WebElement from1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[1]/div/div/div[1]/input"));
        new Actions(driver).click(from1)
                .pause(Duration.ofSeconds(1))
                .sendKeys(from)
                .pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER)
                .perform();
        // 到达地
        WebElement to1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[3]/div/div/div[1]/input"));
        new Actions(driver).click(to1)
                .sendKeys(to)
                .pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER)
                .perform();
        Thread.sleep(600);
        //筛选
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div/div/div/div[1]/i")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/div/div/div[1]/i[1]")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/div/div/div[2]/ul/li[2]/div[2]")).click();
        // 日期
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[2]/div/div[1]/span/div/div/div/input")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[7]/span[1]")).click();
        Thread.sleep(500);
        // 搜索
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/button")).click();
        Thread.sleep(4000);
        takeScreenshot(screenShot);
    }
    @ParameterizedTest
    @CsvSource({
            "12345,广州,test_Ctripflight_R002_001.png",
            "12345456,成都,test_Ctripflight_R002_002.png",
    })
    public void test_Ctripflight_R002(String from, String to, String screenShot) throws InterruptedException {
        // 机票
        driver.findElement(By.xpath("//*[@id=\"leftSideNavLayer\"]/div/div/div[1]/div/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        Thread.sleep(1000);
        //单程
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]/span")).click();
        Thread.sleep(500);
        // 出发地
        WebElement from1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[1]/div/div/div[1]/input"));
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[1]/div/div/div[1]/input")).click();
        Thread.sleep(500);
        new Actions(driver).click(from1)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys(Keys.BACK_SPACE)
                .perform();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[1]/div/div/div[1]/input")).sendKeys(from);
        Thread.sleep(200);
        takeScreenshot(screenShot);
    }
    @ParameterizedTest
    @CsvSource({
            "北京,东京,test_Ctripflight_R003_001.png",
            "北京,大阪,test_Ctripflight_R003_002.png",
            "上海,东京,test_Ctripflight_R003_003.png",
            "上海,大阪,test_Ctripflight_R003_004.png"
    })
    public void test_Ctripflight_R003(String from, String to, String screenShot) throws InterruptedException {
        // 机票
        driver.findElement(By.xpath("//*[@id=\"leftSideNavLayer\"]/div/div/div[1]/div/div")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[2]/button")).click();
        Thread.sleep(1000);
        //单程
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[1]/ul/li[1]/span")).click();
        Thread.sleep(500);
        // 出发地
        WebElement from1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[1]/div/div/div[1]/input"));
        new Actions(driver).click(from1)
                .pause(Duration.ofSeconds(1))
                .sendKeys(from)
                .pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER)
                .perform();
        // 到达地
        WebElement to1 = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[1]/div/div[3]/div/div/div[1]/input"));
        new Actions(driver).click(to1)
                .sendKeys(to)
                .pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER)
                .perform();
        Thread.sleep(600);
        //筛选
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[1]/div/div/i[1]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[2]/div[2]/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[3]/div[2]/div[3]")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[3]/div/div/div/div[2]/div[4]/a[1]")).click();
        // 日期
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/div/div/div[2]/div[2]/div/div[1]/span/div/div/div/input")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div[2]/div[7]/span[1]")).click();
        Thread.sleep(500);
        // 搜索
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div[1]/div/form/div/button")).click();
        Thread.sleep(4000);
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
