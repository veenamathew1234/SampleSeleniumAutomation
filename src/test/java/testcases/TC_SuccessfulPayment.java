package testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.PaymentMethod;
import pages.ShoppingCart;
import utils.StartUp;

public class TC_SuccessfulPayment {
	WebDriver driver;
	HomePage hm=new HomePage(driver);
	StartUp st=new StartUp();
	ShoppingCart sc=new ShoppingCart(driver);
	PaymentMethod pm=new PaymentMethod();
	Map<String,Object> userdetails;
	Map<String,Object> creditcarddetails;
	
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
  
  @Test(priority=4)
  public void verifyOrderSummaryToContinue()
  {
	  sc.verifyOrderSummaryPage();
  }
  @Test(priority =5)
  public void choosePaymentMethod()
  {
	  pm.chooseAPaymentMethod(userdetails);
  }
  @Test(priority =6)
  public void enterCreditCardDetails()
  {
	  creditcarddetails=(Map<String, Object>) userdetails.get("CreditCardInfo");
	  pm.enterCreditCardDetailsAndCompletePayment(creditcarddetails);
  }
  
  @Test(priority=7)
  public void verifyPaymentSuccess()
  {
	  boolean result=pm.verifyPurchaseSuccess();
	  Assert.assertEquals(result, true, "Successful payment message didnt get displayed");
  }
}
