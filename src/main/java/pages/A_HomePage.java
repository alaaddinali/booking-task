package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class A_HomePage {

    private final WebDriver driver;
    protected Helper helper;
    public A_HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By closeSignInButton = By.xpath("//button[@aria-label='Dismiss sign-in info.']");
    private final By destinationTextField = By.xpath("//input[@name='ss']");
    private final By checkInDateField = By.xpath("//button[@data-testid='date-display-field-start']");
    private final By nextMonthButton = By.xpath("//button[@class='fc63351294 a822bdf511 e3c025e003 " +
            "fa565176a8 cfb238afa1 c334e6f658 ae1678b153 c9fa5fc96d be298b15fa']");
    private final By searchButton = By.xpath("//button[@type='submit']");

    public A_HomePage closeSignInPopup() {
        helper = new Helper(driver);
        helper.waitUntilElementIsVisible(20, closeSignInButton);
        driver.switchTo().activeElement();
        driver.findElement(closeSignInButton).click();
        driver.switchTo().defaultContent();
        return this;
    }

    public A_HomePage enterDestination(String destination){
        driver.findElement(destinationTextField).sendKeys(destination);
        return this;
    }

    public A_HomePage selectDate(String checkInDate, String checkOutDate) {
        driver.findElement(checkInDateField).click();
        while (true) {
            try {
                driver.findElement(By.xpath("//span[@data-date='" + checkInDate + "']")).click();
                driver.findElement(By.xpath("//span[@data-date='" + checkOutDate + "']")).click();
                break;
            } catch (Exception e) {
                driver.findElement(nextMonthButton).click();
            }
        }
        return this;
    }

    public B_SearchResultPage clickSearchButton(){
        helper = new Helper(driver);
        helper.waitUntilElementIsClickable(20, searchButton).click();
        return new B_SearchResultPage(driver);
    }
}
