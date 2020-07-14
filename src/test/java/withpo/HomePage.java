package withpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Base;

public class HomePage extends Base
{
	private String title = "The Internet";
	
	
//elements 
	@FindBy(id = "flash")
	@CacheLookup 
	private WebElement confirmLogin;
	
	
	@FindBy(css = ".button.secondary.radius")
	@CacheLookup 
	private WebElement buttonLoguot;
	
//constructor
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		assertEquals(title, driver.getTitle());
	}
	
//services
	public LoginPage logout()
	{
		clickElement(buttonLoguot);
		return PageFactory.initElements(driver, LoginPage.class);
		
	}
	
	public String getLoginConfirmation()
	{
		return confirmLogin.getText();
	}
	
}
