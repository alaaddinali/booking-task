package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class D_ReservationPage {

    private final WebDriver driver;

    public D_ReservationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By hotelName = By.xpath("//h1[@class='ac78a73c96']");
    private final By checkInDate = By.xpath("(//span[@class='bui-date__title'])[1]");
    private final By checkOutDate = By.xpath("(//span[@class='bui-date__title'])[2]");

    public String getHotelName() {
        return driver.findElement(hotelName).getText();
    }

    public String getCheckInDate() {
        return driver.findElement(checkInDate).getText();
    }

    public String getCheckOutDate() {
        return driver.findElement(checkOutDate).getText();
    }
}
