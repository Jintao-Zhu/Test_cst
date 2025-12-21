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

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;



public class TestBaiDuMap {
    private WebDriver driver;
    
    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://map.baidu.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

     // test-code-start

    // 请在此处插入Selenium+JUnit5代码

    @ParameterizedTest
    @CsvSource({
            "南京大学(鼓楼校区),新街口商业步行区,BaiDuMap_R001_001.png",
            "南京大学(鼓楼校区),先锋书店(五台山店),BaiDuMap_R001_002.png",
            "东南大学(四牌楼校区),新街口商业步行区,BaiDuMap_R001_003.png",
            "东南大学(四牌楼校区),先锋书店(五台山店),BaiDuMap_R001_004.png"
    })
    public void test_BaiDuMap_R001(String departure, String destination, String screenshotName) throws InterruptedException {

        // 点击【路线】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[2]")).click();
        // 点击左上角的【公交】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
        // 输入起点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/input")).sendKeys(departure);
        // 输入终点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/input")).sendKeys(destination);
        // 点击搜索按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/button")).click();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }

    @ParameterizedTest
    @CsvSource({
            "南京大学(鼓楼校区),南京大学(仙林校区)"
    })
    public void test_BaiDuMap_R002(String departure, String destination) throws InterruptedException {

        // 点击【路线】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[2]")).click();
        // 点击左上角的【公交】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
        // 输入起点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/input")).sendKeys(departure);
        // 输入终点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/input")).sendKeys(destination);
        // 点击搜索按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/button")).click();
        Thread.sleep(3000);
        // 【推荐路线】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[2]/table/tbody/tr/td[1]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R002_001.png");
        // 【时间短】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[2]/table/tbody/tr/td[2]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R002_002.png");
        // 【少换乘】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[2]/table/tbody/tr/td[3]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R002_003.png");
        // 【少步行】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[2]/table/tbody/tr/td[4]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R002_004.png");
    }

    @Test
    public void test_BaiDuMap_R003() throws InterruptedException {

        // 点击【路线】按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div[2]")).click();
        // 点击左上角的【公交】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[1]/div[1]/div[1]")).click();
        // 输入起点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[1]/input")).sendKeys("玄武湖景区");
        // 输入终点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div/div[2]/div[2]/input")).sendKeys("先锋书店（五台山店）");
        // 点击搜索按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/button")).click();
        Thread.sleep(3000);
        // 点击【选为起点】
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div/div[2]/div/div[2]/div/div")).click();
        Thread.sleep(3000);
        // 点击【查看全部线路】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[2]/div[2]/a")).click();
        Thread.sleep(3000);
        // 选择并点击【48路】
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/ul[2]/li/div[1]/div[3]/div/div[1]/ul/li[3]/div/div[1]/div")).click();
        Thread.sleep(3000);
        // 双击摄像头
        driver.findElement(By.xpath("//*[@id=\"route_2\"]/h3[2]/div/a")).click();
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(By.xpath("//*[@id=\"route_2\"]/h3[2]/div/a"))).perform();
        Thread.sleep(8000);
        takeScreenshot("BaiDuMap_R003_001.png");
    }

    @Test
    public void test_BaiDuMap_R004() throws InterruptedException {

        // 点击【地点下拉选择】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[1]/a")).click();
        // 选择【南京】
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div[1]/div[3]/a[8]")).click();
        // 点击【路况】 选项
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[2]/div[1]")).click();
        // 点击【实时路况】 按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[1]/div[1]/span[1]")).click();
        // 单击【刷新】 按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/span[3]")).click();
        Thread.sleep(5000);
        takeScreenshot("BaiDuMap_R004_001.png");
    }

    @Test
    public void test_BaiDuMap_R005() throws InterruptedException {

        // 点击【地点下拉选择】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[1]/a")).click();
        // 选择【南京】
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div[1]/div[3]/a[8]")).click();
        // 点击【路况】 选项
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[2]/div[1]")).click();
        // 点击【路况预测】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[1]/div[1]/span[2]")).click();
        Thread.sleep(3000);
        // 点击【星期标签】 1
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[2]/span")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_001.png");
        // 点击【星期标签】 2
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[3]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_002.png");
        // 点击【星期标签】 3
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[4]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_003.png");
        // 点击【星期标签】 4
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[5]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_004.png");
        // 点击【星期标签】 5
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[6]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_005.png");
        // 点击【星期标签】 6
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[7]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_006.png");
        // 点击【星期标签】 7
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[8]")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R005_007.png");

    }

    @Test
    public void test_BaiDuMap_R006() throws InterruptedException {

        // 点击【地点下拉选择】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[1]/a")).click();
        // 选择【南京】
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div[1]/div[3]/a[8]")).click();
        // 点击【路况】 选项
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[2]/div[1]")).click();
        Thread.sleep(3000);
        // 点击【路况预测】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[3]/div[1]/div/div/div[1]/div[1]/span[2]")).click();
        Thread.sleep(3000);
        // 将时间轴拖动至晚上21:00
        Actions action = new Actions(driver);
        action.clickAndHold(driver.findElement(By.id("bar_trafficCtrl"))).moveByOffset(40,0).release().perform();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R006_001.png");
    }

    @ParameterizedTest
    @CsvSource({
            "珠江路,南京站,BaiDuMap_R007_001.png",
            "珠江路,卡子门,BaiDuMap_R007_002.png",
            "新街口,南京站,BaiDuMap_R007_003.png",
            "新街口,卡子门,BaiDuMap_R007_004.png"
    })
    public void test_BaiDuMap_R007(String departure, String destination, String screenshotName) throws InterruptedException {

        // 点击【地点下拉选择】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[1]/a")).click();
        // 选择【南京】
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div[1]/div[3]/a[8]")).click();
        // 点击【地铁】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[2]/div[2]")).click();
        // 输入起点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/input")).sendKeys(departure);
        // 输入终点
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[2]/input")).sendKeys(destination);
        // 点击搜索按钮
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/button")).click();
        Thread.sleep(3000);
        // 截图
        takeScreenshot(screenshotName);
    }

    @Test
    public void test_BaiDuMap_R008() throws InterruptedException {

        // 点击【地点下拉选择】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[1]/a")).click();
        Thread.sleep(3000);
        // 选择【南京】
        driver.findElement(By.xpath("/html/body/div[5]/div[1]/div[2]/div/div[1]/div[3]/a[8]")).click();
        Thread.sleep(3000);
        // 点击【地铁】
        driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[3]/div/div[2]/div[2]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/*[name()='svg']")).click();
        // 点击【大行宫站】 作为起点
        driver.findElement(By.name("大行宫")).click();
        Thread.sleep(3000);
        // 点击【马群站】 作为终点
        driver.findElement(By.name("马群")).click();
        Thread.sleep(3000);
        // search
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/button")).click();
        Thread.sleep(3000);
        takeScreenshot("BaiDuMap_R008_001.png");
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