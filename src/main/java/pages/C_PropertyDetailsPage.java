package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import utilities.Helper;

public class C_PropertyDetailsPage {
    private final WebDriver driver;
    protected Helper helper;
    public C_PropertyDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By selectAmountDDl = By.xpath("//select[@name='nr_rooms_78883120_91939502_0_33_0_41999']");
    private final By reserveButton = By.xpath("//span[@class='bui-button__text js-reservation-button__text']");

    public C_PropertyDetailsPage selectFromDropdown(int option) {
        helper = new Helper(driver);
        helper.waitUntilElementIsVisible(20, selectAmountDDl);
        Select dropdownElement = new Select(driver.findElement(selectAmountDDl));
        dropdownElement.selectByIndex(option);
        return this;
    }

    public D_ReservationPage reserveStay(){
        driver.findElement(reserveButton).click();
        return new D_ReservationPage(driver);
    }

}
