package testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ShoppingCart;
import utils.StartUp;

public class TC_SuccessfulPayment {
	WebDriver driver;
	HomePage hm=new HomePage(driver);
	StartUp st=new StartUp();
	ShoppingCart sc=new ShoppingCart(driver);
	Map<String,Object> userdetails;
	
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser)
	{
		st.fetchDriver(browser);
		
	}
	
	@BeforeClass
	public void loadJsonData()
	{
		userdetails=st.loadJsonData("UserInfoPaySuccess.json");
	}

	
  @Test(priority=1)
  public void launchWebsite() {
	  hm.launchPage();
	  
  }
  
  @Test(priority=2)
  public void buyProductAndVerifyIfShoppingCartDisplayed() {
	  hm.clickOnBuyNow();
  }
  
  @Test(priority=3)
  public void enterShoppingCartDetails()
  {
		  sc.enterShoppingCartDetails(userdetails);
  }
  
}
