package testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.StartUp;

public class TC_SuccessfulPayment {
	WebDriver driver;
	HomePage hm=new HomePage(driver);
	StartUp st=new StartUp();
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser)
	{
		st.fetchDriver(browser);
		
	}

	
  @Test(priority=1)
  public void launchWebsite() {
	  hm.launchPage();
	  
  }
  
  @Test(priority=2)
  public void buyProductAndVerifyIfShoppingCartDisplayed() {
	  hm.clickOnBuyNow();
  }
  
}
