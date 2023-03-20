package tests;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pages.BasePage;
import pages.HomePage;

public class MainPageTest extends BasePage{

	@Test(description="Checking for cookies",priority=0)
	public void checkForCookies() {
		HomePage homePage = new HomePage(driver);
		homePage.acceptCookies();
		logger.log(LogStatus.PASS, "Accepted cookies");
	}
	//* Tests different date and location using data from DataProvider
	@DataProvider(name="flightRoute")
	public Object[][] getData(){
		return new Object[][] {
				{"CGN", "BER","today"}, //* flight status from CGN to BER for today are displayed
				{"Cologne-Bonn", "Berlin Brandenburg","tomorrow"}, //* flight status from CGN to BER for tomorrow are displayed
				{"Cologne-Bonn", "Berlin Brandenburg","dayAfterTomorrow"}, //* flight status from CGN to BER for Day after tomorrow are displayed
				{"London (All Airports)", "Adana","today"} //* flight status for invalid route  displays appropriate message
		};
	}
	@Test(description = "Checking flight routes " ,priority=1,dataProvider = "flightRoute")
	public void checkFlightRoute(String fromLoc, String toLoc,String dDate)  {
		HomePage homePage = new HomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.navigate().refresh();
		homePage.typeDeparture(fromLoc);
		logger.log(LogStatus.INFO, "DEPARTURE: "+fromLoc);
		homePage.typeArrival(toLoc);
		logger.log(LogStatus.INFO, "ARRIVAL: "+toLoc);
		js.executeScript("window.scrollBy(0,600)");
		homePage.enterDate(dDate);
	}
}	
