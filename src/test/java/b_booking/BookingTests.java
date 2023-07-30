package b_booking;

import a_base.BaseTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.D_ReservationPage;
import testData.ExcelReader;

import java.io.IOException;

public class BookingTests extends BaseTests {

    @DataProvider(name="booking data")
    public Object[][] bookStayData() throws IOException {
        // get data from Excel Reader class
        ExcelReader excelReader = new ExcelReader();
        return excelReader.getExcelData(0);
    }

    @Test (dataProvider = "booking data")
    public void bookStay(String destination, String checkinDate, String checkOutDate, String hotelName,
                         String checkInData, String checkOutData){
        D_ReservationPage reservationPage = new D_ReservationPage(driver);
        homePage.closeSignInPopup()
                .enterDestination(destination)
                .selectDate(checkinDate, checkOutDate)
                .enterDestination(destination)
                .clickSearchButton()
                .selectHotel(hotelName)
                .selectFromDropdown(1)
                .reserveStay();
        Assert.assertEquals(reservationPage.getHotelName(), hotelName);
        Assert.assertEquals(reservationPage.getCheckInDate(), checkInData);
        Assert.assertEquals(reservationPage.getCheckOutDate(), checkOutData);
    }
}
