package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.StartUp;

public class ShoppingCart extends StartUp {

	public ShoppingCart(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public boolean verifyShoppingCart()
	{
		if(driver.findElements(By.xpath("")).size()!=0)
			return true;
		else 
			return false;
	}
}
