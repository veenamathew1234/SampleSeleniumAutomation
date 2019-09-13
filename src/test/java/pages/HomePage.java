package pages;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utils.StartUp;

public class HomePage extends StartUp {

	//WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	
public void launchPage(){
		
		driver.get("https://demo.midtrans.com/");
		String title=driver.getTitle();
		Assert.assertEquals("Sample Store", title, "Title of the webpage loaded not matching. URL could be wrong!!");
	}

}
