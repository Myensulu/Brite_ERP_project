package Brite_erp_hw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.SeleniumUtils;
import java.util.concurrent.TimeUnit;

public class CRM_Delete_Opportunity {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://34.220.250.213/web/login");
    }

    @Test(description = "Delete an opportunity in CRM module")
    public void Test(){
        // Login
        driver.findElement(By.xpath("//input[@id='login']")).sendKeys("eventscrmmanager46@info.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("eventscrmmanager");
        SeleniumUtils.waitPlease(4);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        SeleniumUtils.waitPlease(4);

        // Select CRM and verify the Page Title
        driver.findElement(By.xpath("(//span[@class='oe_menu_text'])[5]")).click();
        SeleniumUtils.waitPlease(4);
        WebElement PipelinePage = driver.findElement(By.linkText("Pipeline"));
        Assert.assertTrue(PipelinePage.getText().equals("Pipeline"));
        SeleniumUtils.waitPlease(2);

        // Create opportunity
        driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
        driver.findElement(By.xpath("//input[@placeholder='e.g. Customer Deal']")).sendKeys("Cyber");
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("(//input[@class='o_input ui-autocomplete-input'])[1]")).click();
        driver.findElement(By.linkText("111 Hello Street")).click();
        SeleniumUtils.waitPlease(4);

        WebElement expectedRevenue= driver.findElement(By.cssSelector("input[name='planned_revenue']"));
        Actions action=new Actions(driver);
        action.doubleClick(expectedRevenue).sendKeys("850").build().perform();
        SeleniumUtils.waitPlease(2);

        // --> /../ means parent element
        driver.findElement(By.xpath("//label[text()='Priority']/../following-sibling::td//a[@title='Very High']")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.cssSelector("button[name='close_dialog']")).click();
        SeleniumUtils.waitPlease(3);

        // Delete opportunity
        driver.findElement(By.xpath("//button[@accesskey='l']")).click();
        SeleniumUtils.waitPlease(2);
        String beforeResultStr = driver.findElement(By.cssSelector("span[class='o_pager_limit']")).getText();
        System.out.println(beforeResultStr);
        driver.findElement(By.xpath("(//input[@type='checkbox'])[4]")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("//button[contains(text(),'Action')]")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.linkText("Delete")).click();
        SeleniumUtils.waitPlease(2);
        driver.findElement(By.xpath("//span[.='Ok']")).click();
        SeleniumUtils.waitPlease(3);
        String afterResultStr = driver.findElement(By.cssSelector("span[class='o_pager_limit']")).getText();
        System.out.println(afterResultStr);
        // convert String to int
        int beforeResult = Integer.parseInt(beforeResultStr);
        int afterResult = Integer.parseInt(afterResultStr);
        // compare the results
        Assert.assertTrue(beforeResult>afterResult);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
