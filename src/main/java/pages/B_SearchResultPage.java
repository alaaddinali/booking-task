package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

import java.util.ArrayList;

public class B_SearchResultPage {
    private final WebDriver driver;
    protected Helper helper;
    public B_SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nextPagingButton = By.xpath("//button[@aria-label='Next page']");

    public C_PropertyDetailsPage selectHotel (String hotelName){
        By hotel = By.xpath("//div[contains(text(), '" + hotelName + "')]");
        helper = new Helper(driver);
        helper.waitUntilElementIsVisible(20, nextPagingButton);
        while (true){
            try{
                driver.findElement(hotel).click();
                break;
            } catch (Exception e) {
                driver.findElement(nextPagingButton).click();
                helper.waitUntilElementIsVisible(20, hotel);
            }
        }
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new C_PropertyDetailsPage(driver);
    }
}
