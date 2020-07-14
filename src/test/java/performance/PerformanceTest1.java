package performance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

class PerformanceTest1 {

	private WebDriver driver;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.logfile", "TestLog.txt");
	}

	@BeforeEach
	void setUp() throws Exception 
	{
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/login");
		
	}

	@AfterEach
	void tearDown() throws Exception 
	{
		driver.quit();
	}

	@Test
	void testUsername() 
	{
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.username.sendKeys("Yelena");
		var value = loginPage.username.getAttribute("value");
		System.out.println(value);
	}

}
