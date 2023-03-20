package pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static pages.BasePage.logger;

public class HomePage {
	By fromBar = By.xpath("//*[@id=\"site\"]/main/div[3]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div[1]/div/div/button/div/span[1]");
	By depart = By.id("station-select-200-input");
	By toBar= By.xpath("//*[@id=\"site\"]/main/div[3]/div[3]/div/div[1]/div/div[2]/div[1]/div/div/div[2]/div/div/button/div");
	By arrival = By.id("station-select-201-input");
	By cookieBtn= By.className("a-cta__container");
	By datePicker = By.className("m-form-datepicker");
	By highlightedDate= By.xpath("//*[@id=\"site\"]/main/div[3]/div[3]/div/div[2]/div/div[2]/nav/ul/li[5]/button/div");
	By showResult= By.xpath("//*[@id=\"site\"]/main/div[3]/div[3]/div/div[1]/div/div[2]/div[1]/form/div[2]/button/span[2]/span");
	By errorMsg= By.xpath("//*[@id=\"site\"]/main/div[3]/div[3]/div/div[2]/div/div[2]/div/div/h2");
	WebDriver driver;
	WebDriverWait wait;
	DateFormat dateFormat;
	public HomePage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, 5);
	}
	public void acceptCookies() {
		if(driver.findElement(cookieBtn).isDisplayed())
		{
			driver.findElement(cookieBtn).click();
		}
	}
	public void typeDeparture(String fromLoc) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(fromBar)).isDisplayed();
		driver.findElement(fromBar).click();
		driver.findElement(depart).sendKeys(fromLoc);
		new Actions(driver).sendKeys(Keys.ENTER).perform();
	}
	public void typeArrival(String toLoc) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(toBar)).isDisplayed();
		driver.findElement(toBar).click();
		driver.findElement(arrival).sendKeys(toLoc);
		new Actions(driver).sendKeys(Keys.ENTER).perform();
	}
	public void enterDate(String dDate) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(datePicker)).isDisplayed();
		driver.findElement(datePicker).click();
		Calendar calendar = Calendar.getInstance();
		dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date day;
		if(dDate.contentEquals("today"))
		{
			logger.log(LogStatus.INFO, "DATE : Today");
		}
		else if (dDate.contentEquals("tomorrow"))
		{
			logger.log(LogStatus.INFO, "DATE: Tomorrow");
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		else if(dDate.contentEquals("dayAfterTomorrow"))
		{
			logger.log(LogStatus.INFO, "DATE: Day after tomorrow");
			calendar.add(Calendar.DAY_OF_YEAR, 2);
		}
		else
		{
			logger.log(LogStatus.FAIL, "Wrong date inputted");
		}
		day = calendar.getTime();
		String selectDate = dateFormat.format(day);
		driver.findElement(By.cssSelector("input[value='"+selectDate+"']")).click();
		logger.log(LogStatus.PASS, "Result for requested date: "+selectDate);
		this.showFlights();
		this.checkResult(day);
	}
	public void checkResult(Date selectDate){
		wait.until(ExpectedConditions.visibilityOfElementLocated(highlightedDate)).isDisplayed();
		dateFormat =new SimpleDateFormat("EEE, dd/MM/");
		String assignedDate = dateFormat.format(selectDate);
		String resultDate=driver.findElement(highlightedDate).getText();
		Assert.assertTrue(resultDate.equals(assignedDate));
		List<WebElement> elements = driver.findElements(errorMsg);
		if (elements.isEmpty())
		{
			logger.log(LogStatus.PASS, "Flight route for entered flight route has been generated");
		}
		else
		{
			logger.log(LogStatus.PASS, "There is no Flights for the selected date");
		}
	}
	public void showFlights() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(showResult)).isDisplayed();
		driver.findElement(showResult).click();
	}
}
