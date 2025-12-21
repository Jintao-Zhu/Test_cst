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
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Objects;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test12306 {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
//         System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");

        // 无头模式
//        chromeOptions.addArguments("--headless");

        driver = new ChromeDriver(chromeOptions);
        driver.get("http://121.40.102.53:7012/"); // 企业网盘
//        driver.get("http://121.40.102.53:7011/"); // 后台管理
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


//    @org.junit.jupiter.api.Test
//    public void testUI() {
//        takeScreenshot("12306_search_UI_001.png");
//    }

    @ParameterizedTest
    @CsvSource({
            "test,test,12306_R001_001.png",
            "admin,123456,12306_R001_002.png",
    })
    public void test_12306_R001(String account, String password, String screenShot) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[1]/div/div/input")).sendKeys(account);
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[2]/div/div/input")).sendKeys(password);
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/div[3]/div/button")).click();

//        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[3]/div/button")).click();

        //test.txt
//        driver.findElement(By.xpath("/html/body/div/div/section/section/main/div/div[3]/div/div/div[3]/table/tbody/tr/td[2]/div/span")).click();
//        driver.findElement(By.xpath("/html/body/div[1]/div/section/section/main/div/div[5]/div/div[2]/div/div/div/div[1]/div[2]/button")).click();
        //通知
        driver.findElement(By.xpath("/html/body/div[1]/div/section/header/div/div[3]/span[1]/span/div/button")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[1]/div/button")).click();

        //退出
        driver.findElement(By.xpath("/html/body/div[1]/div/section/header/div/div[3]/span[2]/span/button")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[1]/button[2]")).click();

        takeScreenshot(screenShot);
    }


    @ParameterizedTest
    @CsvSource({
            "北京,广州,2024-10-25,12306_R002_001.png,无",
            "北京,广州,2024-11-10,12306_R002_002.png,无",
            "北京,广州,2024-10-32,12306_R002_003.png,无"
    })
    public void test_12306_R002(String from, String to, String fromTime, String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[4]/a")).click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);
        takeScreenshot(screenShot);
    }

    @ParameterizedTest
    @CsvSource({
            "北京,上海,2024-10-30,12306_R003_001.png,12306_R003_002.png,车次类型",
            "北京,上海,2024-10-30,12306_R003_003.png,12306_R003_004.png,出发车站",
            "北京,上海,2024-10-30,12306_R003_005.png,12306_R003_006.png,到达车站",
            "北京,上海,2024-10-30,12306_R003_007.png,12306_R003_008.png,车次席别"
    })
    public void test_12306_R003(String from, String to, String fromTime, String screenShot1, String screenShot2, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[1]/div[1]/div[4]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);

        switch (config) {
            case "车次类型":
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[2]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot1);
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[2]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot2);
                break;
            case "出发车站":
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[3]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot1);
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[3]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot2);
                break;
            case "到达车站":
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[4]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot1);
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[4]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot2);
                break;
            case "车次席别":
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[5]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot1);
                driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[5]/div[2]/ul/li[1]/input")).click();
                takeScreenshot(screenShot2);
                break;
        }

    }


    @ParameterizedTest
    @CsvSource({
            "北京,上海,2024-11-01,2024-11-01,12306_R004_001.png,无",
            "北京,上海,2024-11-01,2024-11-05,12306_R004_002.png,无",
            "北京,上海,2024-11-01,2024-11-09,12306_R004_003.png,无"
    })
    public void test_12306_R004(String from, String to, String fromTime, String toTime,  String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div/div/input")).sendKeys(toTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[5]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);
        takeScreenshot(screenShot);

    }


    @ParameterizedTest
    @CsvSource({
            "北京,上海,2024-11-02,2024-11-01,12306_R005_001.png,无",
            "北京,上海,2024-11-02,2024-11-10,12306_R005_002.png,无",
            "北京,上海,2024-11-02,2024-11-00,12306_R005_003.png,无"
    })
    public void test_12306_R005(String from, String to, String fromTime, String toTime,  String screenShot, String config) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[2]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div/div/input")).sendKeys(toTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[5]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);
        takeScreenshot(screenShot);

    }

    @Test
    public void test_12306_R006() throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[2]/a")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/div/div/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div/div[2]/div/div/ul[1]/li[3]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div/div[2]/div/div/div[3]/ul[1]/li[4]")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div/div/input[2]")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[9]/div/div/div[2]/div/div/ul[2]/li[33]")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[2]/div/div/input")).sendKeys("2024-10-30");
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[3]/div/div/input")).sendKeys("2024-11-06");

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[4]/div/ul/li[1]/i")).click();

        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div/div[5]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(3000);

        driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[1]/ul/li[9]/span[2]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[10]/div[2]/div[2]/div[2]/ul/li[8]")).click();
        driver.findElement(By.xpath("/html/body/div[2]/div[8]/div[11]/span/label[1]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[7]/div[9]/form/div[3]/div/a")).click();

        takeScreenshot("12306_R006_001.png");

    }


    @ParameterizedTest
    @CsvSource({
            "哈尔滨,济南,2024-10-27,12306_R007_001.png",
            "哈尔滨,西安,2024-10-27,12306_R007_002.png",
            "哈尔滨,拉萨,2024-10-27,12306_R007_003.png",
            "长春,济南,2024-10-27,12306_R007_004.png",
            "长春,西安,2024-10-27,12306_R007_005.png",
            "长春,拉萨,2024-10-27,12306_R007_006.png",
            "安吉,济南,2024-10-27,12306_R007_007.png",
            "安吉,西安,2024-10-27,12306_R007_008.png",
            "安吉,拉萨,2024-10-27,12306_R007_009.png"
    })
    public void test_12306_R007(String from, String to, String fromTime, String screenShot) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[4]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);
        takeScreenshot(screenShot);

    }

    @ParameterizedTest
    @CsvSource({
            "北京,上海,2024-10-27, ,12306_R008_001.png",
            "北京,上海,2024-10-27,@,12306_R008_002.png",
            "北京,上海,2024-10-27,西安,12306_R008_003.png",
    })
    public void test_12306_R008(String from, String to, String fromTime, String location, String screenShot) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[4]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/span/input")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/div/input[2]")).sendKeys(location + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/a")).click();
        Thread.sleep(1000);

        takeScreenshot(screenShot);

    }


    @ParameterizedTest
    @CsvSource({
            "哈尔滨,南京,2024-11-17,济南,12306_R009_001.png",
            "哈尔滨,南京,2024-11-17,安阳,12306_R009_002.png",
            "哈尔滨,南京,2024-11-17,周口,12306_R009_003.png",
    })
    public void test_12306_R009(String from, String to, String fromTime, String location, String screenShot) throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[4]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/span/input")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/div/input[2]")).sendKeys(location +Keys.ENTER);
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/a")).click();
        Thread.sleep(1000);

        takeScreenshot(screenShot);

    }

    @ParameterizedTest
    @CsvSource({
            "哈尔滨,南京,2024-10-27,济南,12306_R009_001.png",
            "哈尔滨,南京,2024-10-27,安阳,12306_R009_002.png",
            "哈尔滨,南京,2024-10-27,周口,12306_R009_003.png",
    })
    public void test_12306_R010(String from, String to, String fromTime, String location, String screenShot) throws InterruptedException {
        driver.manage().window().maximize();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[1]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[1]/div/div/input[2]")).sendKeys(from + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[1]/div[2]/div/div/input[2]")).sendKeys(to + Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).clear();
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[2]/div/div/input")).sendKeys(fromTime);
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div/div[1]/div/div[2]/div[3]/div/div[4]/a")).click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString()); //切换到新打开的窗口
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/span/input")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/ul/li[4]/div/input[2]")).sendKeys(location +Keys.ENTER);
        Thread.sleep(5000);

        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/a")).click();
        Thread.sleep(1000);

        takeScreenshot(screenShot);

    }



    @AfterEach
    public void teardown() {
        this.driver.quit();
    }

    private void takeScreenshot(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmmssSSS");
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

//    private void takeScreenshot(String fileName) {
//        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        try {
//            FileUtils.copyFile(screenshotFile, new File("screenshots/" + fileName));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
