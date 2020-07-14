package withpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import common.Base;

public class LoginPage extends Base
{
	private  String URL = "https://the-internet.herokuapp.com/login";
	private  String title = "The Internet";
	
// elements
	@FindBy(id = "username")
	@CacheLookup 
	private WebElement username;
	
	@FindBy(id = "password")
	@CacheLookup 
	private WebElement password;
	
	@FindBy(tagName = "button")
	@CacheLookup 
	private WebElement buttonLogin;
	
	@FindBy(id = "flash")
	@CacheLookup 
	private WebElement confirmLoguot;
	
	
//constructor
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
// services
	public  LoginPage open()
	{
		driver.get(URL);
		assertEquals(title, driver.getTitle());
		return this;
//				PageFactory.initElements(driver, LoginPage.class); // proxy object
				
	}
	
	public void submitLogin(String user, String pass)
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		clickElement(buttonLogin);
		
	}
	
	public HomePage validLogin(String user, String pass)
	{
		submitLogin(user, pass);
		return PageFactory.initElements(driver, HomePage.class);
		
	}
	
	public LoginPage invalidLogin(String user, String pass)
	{
		submitLogin(user, pass);
		return this;
	}
	
	public String getLogoutConfirmation()
	{
		return confirmLoguot.getText();
	}
	
	
}
