package withpo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

class LoginLogoutPO {

	private WebDriver driver;
	private LoginPage loginPage; 
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp() throws Exception {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		loginPage = new LoginPage(driver);
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.close();
	}

	@Test
	void valid() 
	{
	
		HomePage homePage = loginPage.open()
				.validLogin("tomsmith", "SuperSecretPassword!");
		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
	loginPage = homePage.logout();
	assertTrue(loginPage.getLogoutConfirmation().contains("You logged out"));
		
		
//		HomePage homePage = LoginPage.open(driver)
//						.validLogin("tomsmith", "SuperSecretPassword!");
//		assertTrue(homePage.getLoginConfirmation().contains("You logged into"));
//		LoginPage loginPage = homePage.logout();
//		assertTrue(loginPage.getLogoutConfirmation().contains("You logged out"));
//		
	}
	
	@ParameterizedTest 
	@CsvFileSource(resources = "passwords.csv", numLinesToSkip = 1)
	void invalid(String user, String password, String errorMessage) throws InterruptedException 
	{
		loginPage.open()
		 		.submitLogin(user, password);
		assertTrue(loginPage.getLogoutConfirmation().contains(errorMessage));
	
//		 loginPage.open()
//				.invalidLogin(user, password);
//		assertFalse(loginPage.getLogoutConfirmation().contains("You logged out"));

	}

}
