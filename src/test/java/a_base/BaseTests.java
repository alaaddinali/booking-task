package a_base;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.A_HomePage;

import java.io.File;
import java.io.IOException;

public class BaseTests {

    protected WebDriver driver;
    protected A_HomePage homePage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.booking.com");
        homePage = new A_HomePage(driver);
    }

    @AfterMethod
    public void takeScreenshot(ITestResult result){

        var camera = (TakesScreenshot)driver;
        File screenshot = camera.getScreenshotAs(OutputType.FILE);

        try {
            Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

   @AfterClass
    public void tearDown () {
        driver.quit();
    }
}
