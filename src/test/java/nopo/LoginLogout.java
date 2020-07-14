package nopo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import common.Base;
import io.github.bonigarcia.wdm.WebDriverManager;

class LoginLogout extends Base {

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	void setUp() throws Exception {
		
		driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/login");
		driver.manage().window().maximize();
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	void test() {
		driver.findElement(By.id("username")).sendKeys("tomsmith");
		driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
		WebElement buttonLogin = driver.findElement(By.tagName("button"));
//		buttonLogin.click();
		clickElement(buttonLogin);
		
		WebElement confirmLogin = driver.findElement(By.id("flash"));
		assertTrue(confirmLogin.getText().contains("You logged into"));
		
		WebElement buttonLogout = driver.findElement(By.cssSelector(".button.secondary.radius"));
//		buttonLogout.click();
		clickElement(buttonLogout);
		
		
		WebElement confirmLogout = driver.findElement(By.id("flash"));
		assertTrue(confirmLogout.getText().contains("You logged out"));
		
	}

}
