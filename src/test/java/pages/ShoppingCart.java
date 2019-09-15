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

import utils.CommonMethods;
import utils.ObjectFactory;
import utils.StartUp;

public class ShoppingCart extends StartUp {

	ObjectFactory objmap;
	StartUp st=new StartUp();
	CommonMethods cm=new CommonMethods();
	public ShoppingCart(WebDriver driver)
	{
		this.driver=driver;
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/test/java/uimap/ShoppingCart.properties");
	}

	/*
	 * Purpose : To check after clicking on Buy Now button whether shopping cart opens
	 */
	public boolean verifyShoppingCart()
	{
	
		try
		{
			Thread.sleep(2000);
			
			//Checking for shopping cart label
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
	
	/*
	 * Purpose : To enter shopping cart details and click on Checkout.
	 * Entering shopping cart details which includes the user info and shipping details is not part of requirement
	 * This function was written to give the tester an option to test by entering different user data and
	 * not just going for the default data present
	 * 
	 */
	public boolean enterShoppingCartDetails(Map<String,Object> userData)
	{
		try
		{
			WebElement e;
			Thread.sleep(2000);
		//Enter Name
			if(userData.get("Name")!=null)
			{
				String name=userData.get("Name").toString();
				e=driver.findElement(objmap.getLocator("txt_Name"));
				cm.clearAndWriteText(e, e.getAttribute("value").length(), name, driver);
			}
			
		//Enter Email
			if(userData.get("Email")!=null)
			{
			String email=userData.get("Email").toString();
			e=driver.findElement(objmap.getLocator("txt_Email"));
			/* .sendkeys was not working due some element focus issues hence had to opt for actions
			 * This included 4-5 lines of code each time you try to input data hence made a different function
			 * 
			 * Since .clear was not working . We calculate the length of the data auto filled in the text box and pass it to be function 
			 * The function will delete those many characters
			 */
			cm.clearAndWriteText(e, e.getAttribute("value").length(), email, driver);
			}
		//Enter PHoneNumber
			if(userData.get("PhoneNo")!=null)
			{
			String phoneNo=userData.get("PhoneNo").toString();
			e=driver.findElement(objmap.getLocator("txt_PhoneNo"));
			cm.clearAndWriteText(e, e.getAttribute("value").length(), phoneNo, driver);
			}
		//Enter Address
			if(userData.get("Address")!=null)
			{
			String address=userData.get("Address").toString();
			e=driver.findElement(objmap.getLocator("txt_Address"));
			cm.clearAndWriteText(e, e.getText().length(), address, driver);
			}
			
//		//Click On checkout button
			
			System.out.println("driver="+driver);
			WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_Checkout")));
			driver.findElement(objmap.getLocator("btn_Checkout")).click();
			return true;
			
		}
		
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "One of the Shopping cart text fields is not found");
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		
		
		
	}
	
	/*
	 * Purpose: To click on Continue in the order summary page
	 */
	public boolean verifyOrderSummaryPage()
	{
		try
		{
			//Switching to respective frame inorder to access the pop up containing continue button
			driver.switchTo().frame("snap-midtrans");
			WebDriverWait wait = new WebDriverWait(driver,30);
			 wait.until(ExpectedConditions.presenceOfElementLocated(objmap.getLocator("btn_Continue")));
			driver.findElement(objmap.getLocator("btn_Continue")).click();
			return true;
		}
		catch(NoSuchElementException ne)
		{
			Assert.assertNull(ne, "Continue button in order summary page is not present");
			return false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
