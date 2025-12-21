import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class TestZhiHu {
    private WebDriver driver;
    
    @BeforeEach
    public void setup() {
        //提交最终代码脚本时，请将驱动路径换回官方路径"C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe"
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\86153\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.zhihu.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

     // test-code-start

    // 请在此处插入Selenium+JUnit5代码

    @Test
    public void test_ZhiHu_R006() throws InterruptedException {
        // 登录
        Thread.sleep(8000);
        // 写文章
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        // 导入
        driver.findElement(By.cssSelector("button[aria-label='导入']")).click();
        Thread.sleep(2000);
        // 导入文档
        driver.findElement(By.cssSelector("button[aria-label='导入文档']")).click();
        Thread.sleep(2000);
        // 选择文档
        driver.findElement(By.xpath("//div[@role='button' and contains(., '点击选择本地文档')]")).click();
        Thread.sleep(10000);
        // 截图
        takeScreenshot("ZhiHu_R006_001.png");
        Thread.sleep(2000);
    }

    @Test
    public void test_ZhiHu_R007() throws InterruptedException {
        // 登录
        Thread.sleep(8000);
        // 写文章
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        // 导入
        driver.findElement(By.cssSelector("button[aria-label='导入']")).click();
        Thread.sleep(2000);
        // 导入文档
        driver.findElement(By.cssSelector("button[aria-label='导入文档']")).click();
        Thread.sleep(2000);
        // 选择文档
        driver.findElement(By.xpath("//div[@role='button' and contains(., '点击选择本地文档')]")).click();
        Thread.sleep(10000);
        // 编辑图片
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/div/figure/div/div[1]/div/div/div/div/div/div[1]")).click();
        Thread.sleep(2000);
        // 文字
        driver.findElement(By.cssSelector("div.css-d7e6dt > button.ImageEditor-ToolbarButton:first-of-type")).click();
        Thread.sleep(2000);
        // 白色
        driver.findElement(By.cssSelector("div.ImageEditor-ToolbarButton > div.ImageEditor-ColorPickerItem:nth-of-type(6)")).click();
        Thread.sleep(2000);
        // 文本框
        driver.findElement(By.cssSelector("button.ImageEditor-ToolbarButton svg.Zi--TextArea")).findElement(By.xpath("..")).click();
        Thread.sleep(2000);
        // 输入文字


        // 截图
        takeScreenshot("ZhiHu_R007_001.png");
        Thread.sleep(2000);
    }


    @Test
    public void test_ZhiHu_R008() throws InterruptedException {
        // 登录
        Thread.sleep(8000);
        // 写文章
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        // 图片
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div/div/div/div[1]/div[4]/div/button")).click();
        Thread.sleep(2000);
        // 表情包
        driver.findElement(By.xpath("//button[normalize-space()='表情包']")).click();
        Thread.sleep(2000);
        // 1
        driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div[2]/div/div/div/div[2]/div[2]/div[2]/div[1]/img")).click();
        Thread.sleep(2000);
        // 插入图片
        driver.findElement(By.xpath("//button[normalize-space()='插入图片']")).click();
        Thread.sleep(2000);
        // 截图
        takeScreenshot("ZhiHu_R008_001.png");
        Thread.sleep(2000);
    }


    @Test
    public void test_ZhiHu_R009() throws InterruptedException {
        // 登录
        Thread.sleep(8000);
        // 写文章
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        // 图片
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div/div/div/div[1]/div[4]/div/button")).click();
        Thread.sleep(2000);
        // 表情包
        driver.findElement(By.xpath("//button[normalize-space()='表情包']")).click();
        Thread.sleep(2000);
        // 5
        driver.findElement(By.cssSelector("img[src='https://picx.zhimg.com/v2-24dadbd73230df6c9fd76943eac3f4ff_100w.png']")).click();
        Thread.sleep(2000);
        // 6
        driver.findElement(By.cssSelector("img[src='https://picx.zhimg.com/v2-45ff0f64d34c7d8e42f8ca57e4ccbeff_100w.png']")).click();
        Thread.sleep(2000);
        // 插入图片
        driver.findElement(By.xpath("//button[normalize-space()='插入图片']")).click();
        Thread.sleep(2000);

        // 编辑
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/div/figure[2]/div/div[1]/div/div/div/div/div/div[1]")).click();
        Thread.sleep(2000);
        // 裁剪
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[2]/button[3]")).click();
        Thread.sleep(2000);


        // 拖动裁剪??
        WebElement cropHandle = driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/div/div[4]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(cropHandle).moveByOffset(-50, -50).release().perform();
        Thread.sleep(2000);
        // 保存裁剪修改
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[3]/button")).click();
        Thread.sleep(2000);
        // 使用图片
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[4]/button")).click();
        Thread.sleep(2000);
        // 截图
        takeScreenshot("ZhiHu_R009_001.png");
        Thread.sleep(2000);

        // 编辑
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[2]/div[1]/div/div[1]/div/div/div/div/figure[1]/div/div[1]/div/div/div/div/div/div[1]")).click();
        Thread.sleep(2000);
        // 裁剪
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[2]/button[3]")).click();
        Thread.sleep(2000);
        // 拖动裁剪
        cropHandle = driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[2]/div/div/div/div/div[4]"));
        actions = new Actions(driver);
        actions.clickAndHold(cropHandle).moveByOffset(-50, -50).release().perform();
        Thread.sleep(2000);
        // 保存裁剪修改
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[3]/button")).click();
        Thread.sleep(2000);
        // 使用图片
        driver.findElement(By.xpath("/html/body/div[8]/div/div/div/div[2]/div/div/div/div[2]/div[4]/button")).click();
        Thread.sleep(2000);
        // 截图
        takeScreenshot("ZhiHu_R009_002.png");
        Thread.sleep(2000);
    }




    @ParameterizedTest
    @CsvSource({
            "浏览器Cookie管理,driver.manage().addCookie(),ZhiHu_R010_001.png",
            "点击鼠标右键,contextClick(),ZhiHu_R010_002.png",
            "鼠标移动到某个坐标,moveByOffset(),ZhiHu_R010_003.png",
            "'全选','(Keys.CONTROL,\"a\")','ZhiHu_R010_004.png'"
    })
    public void test_ZhiHu_R010(String line1, String line2, String screenshotName) throws InterruptedException {
        // 登录
        Thread.sleep(8000);
        // 写文章
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div[1]/div[2]/div[3]")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        Thread.sleep(2000);
        // 输入标题
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[2]/div/div[1]/label")).sendKeys("Web应用测试");
        Thread.sleep(2000);
        // 表格
        driver.findElement(By.xpath("/html/body/div[1]/div/main/div/div[2]/div[1]/div/div/div/div[1]/div[4]/button[4]")).click();
        Thread.sleep(2000);
        // 行数
        driver.findElement(By.cssSelector("input[placeholder='输入表格行数（最大 100 行）']")).sendKeys("2");
        Thread.sleep(2000);
        // 列数
        driver.findElement(By.cssSelector("input[placeholder='输入表格列数（最大 8 列）']")).sendKeys("1");
        Thread.sleep(2000);
        // 插入
        driver.findElement(By.xpath("//button[text()='插入']")).click();
        Thread.sleep(2000);
        // 选中表格 双击
        WebElement table = driver.findElement(By.cssSelector("div.TableToolbar-container table.Table"));
        Actions actions = new Actions(driver);
        actions.doubleClick(table).perform();
        Thread.sleep(2000);
        driver.findElements(By.cssSelector("div.Table-dataInput[contenteditable='true']")).get(0).click();
        for (int i = 0; i < line1.length(); i++) {
            char c = line1.charAt(i);
            String s = String.valueOf(c);
            driver.findElements(By.cssSelector("div.Table-dataInput[contenteditable='true']")).get(0).sendKeys(s);
            Thread.sleep(100);
        }
        Thread.sleep(2000);
        driver.findElements(By.cssSelector("div.Table-dataInput[contenteditable='true']")).get(1).click();
        for (int i = 0; i < line2.length(); i++) {
            char c = line2.charAt(i);
            String s = String.valueOf(c);
            driver.findElements(By.cssSelector("div.Table-dataInput[contenteditable='true']")).get(1).sendKeys(s);
            Thread.sleep(100);
        }
        Thread.sleep(2000);
        // 预览
        driver.findElement(By.cssSelector("button.Button.Button--secondary.Button--grey")).click();
        Thread.sleep(2000);
        // 电脑预览
        driver.findElement(By.xpath("//button[normalize-space()='电脑预览']")).click();
        Thread.sleep(2000);
        driver.switchTo().window(driver.getWindowHandles().toArray()[2].toString());
        Thread.sleep(2000);
        // 截图
        takeScreenshot(screenshotName);
        Thread.sleep(2000);
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