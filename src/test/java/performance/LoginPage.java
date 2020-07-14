package performance;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{
	@FindBy(id = "username")
	public WebElement username;
	
	@FindBy(id = "username")
	@CacheLookup
	public WebElement usernameCached;

}
