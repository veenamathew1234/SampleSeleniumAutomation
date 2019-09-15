package pages;

import java.sql.Driver;
import java.util.Map;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ObjectFactory;
import utils.StartUp;

public class PaymentMethod extends StartUp {
	
	ObjectFactory objmap;
	public PaymentMethod()
	{
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/test/java/uimap/PaymentMethod.properties");
	}
	
	/*
	 * Purpose:To choose a payment method.Below I have included only for Credit card. 
	 * You can add more if else conditions to include other payment methods
	 */
	
	public boolean chooseAPaymentMethod(Map<String, Object> userdetails)
	{
		try
		{
			if(userdetails.get("CreditCardInfo")!=null)
			{
				driver.findElement(objmap.getLocator("btn_CreditCard")).click();
			}
			return true;
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Payment method cant be located");
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/*
	 * Purpose: To enter read the credit card details from the test data json and 
	 * fill the credit card payment page
	 */
	public boolean enterCreditCardDetailsAndCompletePayment(Map<String, Object> creditcarddetails)
	{
		try
		{
			//Get CardNo From TestData
			String cardno=creditcarddetails.get("CardNumber").toString();
			//Get ExpDate From TestData
			String expdate=creditcarddetails.get("ExpiryDate").toString();
			//CVV from Test Data
			String cvv=creditcarddetails.get("CVV").toString();
			//OTP From Test data
			String otp=creditcarddetails.get("OTP").toString();
			
			//Input data retrieved from test data in to the payment screen
			driver.findElement(objmap.getLocator("txt_CardNo")).sendKeys(cardno);
			driver.findElement(objmap.getLocator("txt_ExpiryDate")).sendKeys(expdate);
			driver.findElement(objmap.getLocator("txt_CVV")).sendKeys(cvv);
			driver.findElement(objmap.getLocator("btn_PayNow")).click();
			Thread.sleep(2000);
			
			//Inorder to access OTP pop up, we needed to switch to the respective frame
			
			WebElement frame=driver.findElement(objmap.getLocator("frame_OTP"));
			driver.switchTo().frame(frame);
			//Wait for the OTP text box to be located
			WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("txt_OTP")));
			driver.findElement(objmap.getLocator("txt_OTP")).sendKeys(otp);;
			driver.findElement(objmap.getLocator("btn_OK")).click();
			return true;
			
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Couldnt locate credit card text fields");
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
	}
	
	/*
	 * Purpose: To check whether in Home Page ,user gets Thank you for your purchase message
	 */
	public boolean verifyPurchaseSuccess()
	{
		try
		{
		
		//We verify successful purchase in home page. For this we need to switch back from the frame we are currently in
			
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_PaymentSuccess")));
		 
		 //If payment success /shopping successful message apperars then we return true
		if(driver.findElements(objmap.getLocator("lbl_PaymentSuccess")).size()!=0)
				return true;
		else return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/*
	 * Purpose: To verify if transaction failed meesage gets displayed when user enters wrong credit card details
	 */

	public boolean verifyPurchaseFailure() {
	
		try
		{
		//Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("frame_FailedTransaction")));	
		WebElement frame=driver.findElement(objmap.getLocator("frame_FailedTransaction"));
		driver.switchTo().frame(frame);
		if(driver.findElements(objmap.getLocator("lbl_TransactionFailed")).size()!=0)
			return true;
		else
			return false;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Transaction Failed message not found");
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
			
		}
		
	}

}
