package testcases;

import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
	boolean result;
	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser)
	{
		st.fetchDriver(browser);
		
	}
	
	@BeforeClass
	/*
	 * Purpose : To load relevant test data for executing this Test Case. 
	 * In this case- Successful credit payment scenario 
	 */
	public void loadJsonData()
	{
		userdetails=st.loadJsonData("UserInfoPaySuccess.json");
	}

	
  @Test(priority=1)
  public void launchWebsite() {
	  result=false;
	  result=hm.launchPage();
	  Assert.assertEquals(result, true, "Unable to launch website successfully");
	  
  }
  
  @Test(priority=2)
  public void buyProductAndVerifyIfShoppingCartDisplayed() {
	  result=false;
	  result=hm.clickOnBuyNow();
	  Assert.assertEquals(result, true, "Buy Now /Verification of Shopping cart didnt happen");
  }
  
  @Test(priority=3)
  public void enterShoppingCartDetails()
  {	
	  result=false;
	  result=sc.enterShoppingCartDetails(userdetails);
	  Assert.assertEquals(result, true, "Error while entering shopping cart details");
  }
  
  @Test(priority=4)
  public void verifyOrderSummaryToContinue()
  {
	  result=false;
	  result=sc.verifyOrderSummaryPage();
	  Assert.assertEquals(result, true, "Error in processing order Summary page (Clicking continue button)");
  }
  @Test(priority =5)
  public void choosePaymentMethod()
  {
	  result=false;
	  result=pm.chooseAPaymentMethod(userdetails);
	  Assert.assertEquals(result, true, "Error in choosing Payment methos function");
  }
  @Test(priority =6)
  public void enterCreditCardDetails()
  {
	  result=false;
	  creditcarddetails=(Map<String, Object>) userdetails.get("CreditCardInfo");
	  result=pm.enterCreditCardDetailsAndCompletePayment(creditcarddetails);
	  Assert.assertEquals(result, true, "Entering credit card details failed");
  }
  
  @Test(priority=7)
  public void verifyPaymentSuccess()
  {
	  result=false;
	  result=pm.verifyPurchaseSuccess();
	  Assert.assertEquals(result, true, "Successful payment message didnt get displayed");
  }
  
//  @AfterClass
//  public void closeDriver()
//  {
//	  driver.quit();
//  }
}
