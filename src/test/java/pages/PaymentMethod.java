package pages;

import java.sql.Driver;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.ObjectFactory;
import utils.StartUp;

public class PaymentMethod extends StartUp {
	
	ObjectFactory objmap;
	public PaymentMethod()
	{
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/test/java/uimap/PaymentMethod.properties");
	}
	
	public void chooseAPaymentMethod(Map<String, Object> userdetails)
	{
		try
		{
			System.out.println("inside payment");
			if(userdetails.get("CreditCardInfo")!=null)
			{
				driver.findElement(objmap.getLocator("btn_CreditCard")).click();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void enterCreditCardDetailsAndCompletePayment(Map<String, Object> creditcarddetails)
	{
		try
		{
			String cardno=creditcarddetails.get("CardNumber").toString();
			String expdate=creditcarddetails.get("ExpiryDate").toString();
			String cvv=creditcarddetails.get("CVV").toString();
			String otp=creditcarddetails.get("OTP").toString();
			driver.findElement(objmap.getLocator("txt_CardNo")).sendKeys(cardno);
			driver.findElement(objmap.getLocator("txt_ExpiryDate")).sendKeys(expdate);
			driver.findElement(objmap.getLocator("txt_CVV")).sendKeys(cvv);
			driver.findElement(objmap.getLocator("btn_PayNow")).click();
			Thread.sleep(2000);
			WebElement frame=driver.findElement(objmap.getLocator("frame_OTP"));
			driver.switchTo().frame(frame);
			WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("txt_OTP")));
			driver.findElement(objmap.getLocator("txt_OTP")).sendKeys(otp);;
			driver.findElement(objmap.getLocator("btn_OK")).click();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	public boolean verifyPurchaseSuccess()
	{
		try
		{
		//Thread.sleep(3000);
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("lbl_PaymentSuccess")));
		if(driver.findElements(objmap.getLocator("lbl_PaymentSuccess")).size()!=0)
				return true;
		else return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}

}
