package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utils.ObjectFactory;
import utils.StartUp;

public class HomePage extends StartUp {

	//WebDriver driver;
	WebDriverWait wait;
	ObjectFactory objmap;
	WebElement e;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		this.objmap=new ObjectFactory(System.getProperty("user.dir")+"/src/test/java/uimap/HomePage.properties");
	}
	ShoppingCart sc=new ShoppingCart(driver);
	
public void launchPage(){
		
		driver.get("https://demo.midtrans.com/");
		String title=driver.getTitle();
		Assert.assertEquals("Sample Store", title, "Title of the webpage loaded not matching. URL could be wrong!!");
	}

//Function to click on Buy Now button
	public void clickOnBuyNow(){
		try
		{
			Thread.sleep(3000);
			e=driver.findElement(objmap.getLocator("btn_BuyNow"));
			e.click();
		
		/*After clicking on Buy Now button , the user should be displayed the shopping cart
		 * 
		 * 
		 */
		//System.out.println(sc.verifyShoppingCart());
		Assert.assertEquals(sc.verifyShoppingCart(), true, "Shopping cart not displayed after BuyNow click");
		}
		catch(Exception e)
		{
			System.out.println("Inside main exception");
			e.printStackTrace();
		}
	}
}
