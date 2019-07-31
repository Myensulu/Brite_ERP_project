package vyTrack_hw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.SeleniumUtils;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://qa2.vytrack.com/user/login");
    }

    @Test(description = "login and logout")
    public void loginTest1() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='login']")).sendKeys("in_pos_manager8@info.com");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("KjKtfgrs37");
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        SeleniumUtils.waitPlease(3);

        // Search button
        driver.findElement(By.xpath("(//span[@class='oe_menu_text'])[8]")).click();
        SeleniumUtils.waitPlease(3);
        driver.findElement(By.xpath("//input[@class='o_searchview_input']")).sendKeys("phone", Keys.ENTER);
        SeleniumUtils.waitPlease(4);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }


}
