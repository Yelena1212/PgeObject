package performance;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

class PerformanceTest2 {

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
	void test() 
	{
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.username.sendKeys("Yelena");
		
		long withoutCachStartTime = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++) 
			loginPage.username.getAttribute("value");
		
		long withoutCachEndTime = System.currentTimeMillis();
		System.out.println("1000 calls for findElement without cach (secs): "
				+ ((withoutCachEndTime - withoutCachStartTime)/ 1000));
		
		long withCachStartTime = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++) 
			loginPage.usernameCached.getAttribute("value");
		
		long withCachEndTime = System.currentTimeMillis();
		System.out.println("1000 calls for findElement with cach (secs): "
				+ ((withCachEndTime - withCachStartTime)/ 1000));
	}

}
