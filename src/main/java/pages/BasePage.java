package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.testng.annotations.BeforeSuite;
public class BasePage {
	public static WebDriver driver;
	String url = "https://www.eurowings.com/en/information/at-the-airport/flight-status.html";
	public static ExtentTest logger;
	public static ExtentReports report;
	@BeforeSuite(alwaysRun=true)
	public void setup(ITestContext context) {
		System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true"); // This suppresses the Severe Timed out receiving messages
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		context.setAttribute("WebDriver", driver);
	}
	@AfterSuite(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
