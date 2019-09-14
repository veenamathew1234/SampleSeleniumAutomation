package pages;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ObjectFactory;
import utils.StartUp;

public class ShoppingCart extends StartUp {

	ObjectFactory objmap;
	StartUp st=new StartUp();
	public ShoppingCart(WebDriver driver)
	{
		this.driver=driver;
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/test/java/uimap/ShoppingCart.properties");
	}
	
	public boolean verifyShoppingCart()
	{
		System.out.println("Inside verify shopping cart");
		
		try
		{
			Thread.sleep(2000);
			
			if(driver.findElements(objmap.getLocator("lbl_ShoppingCartHeading")).size()!=0)
			{
				System.out.println("shopping cart heading found");
				return true;
			}
			else 
				return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public void enterShoppingCartDetails(Map<String,Object> userData)
	{
		try
		{
			Thread.sleep(2000);
		//Enter Name
			String name=userData.get("Name").toString();
			WebElement e=driver.findElement(objmap.getLocator("txt_Name"));
			Actions actions = new Actions(driver);
			actions.moveToElement(e);
			actions.click();
			for(int i=0;i<20;i++)
				actions.sendKeys(Keys.BACK_SPACE);
			actions.sendKeys(name);
			actions.build().perform();
			
//		//Enter Email
//			String email=userData.get("Email").toString();
//			driver.findElement(objmap.getLocator("txt_Email")).sendKeys(email);
//		//Enter PHoneNumber
//			String phoneNo=userData.get("PhoneNo").toString();
//			driver.findElement(objmap.getLocator("txt_PhoneNo")).sendKeys(email);
//		//Enter Address
//			String address=userData.get("Address").toString();
//			driver.findElement(objmap.getLocator("txt_Address")).sendKeys(email);
//		//Click On checkout button
			System.out.println("driver="+driver);
			//System.out.println(driver.getWindowHandle());
			driver.findElement(objmap.getLocator("btn_Checkout")).click();
			
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Shopping cart text field is not found");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
	}
	
	public void verifyOrderSummaryPage()
	{
		try
		{
			Thread.sleep(2000);
//			  for (String activeHandle : driver.getWindowHandles()) {
//			            driver.switchTo().window(activeHandle);
//			        }
			//driver.findElement(By.xpath("//div[contains(@class,'page-container scroll')]")).click();
			driver.switchTo().frame("snap-midtrans");
			System.out.println(driver);
			WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_Continue")));
//			System.out.println(driver.getWindowHandle());
			driver.findElement(objmap.getLocator("btn_Continue")).click();
//			Actions actions = new Actions(driver);
//			actions.moveToElement(e);
//			//Thread.sleep(2000);
//			actions.click();
//			Thread.sleep(2000);
			System.out.println("exiting");
		}
		catch(NoSuchElementException ne)
		{
			System.out.println("Hi");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
