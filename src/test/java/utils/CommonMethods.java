package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonMethods {
	
	/*
	 * Purpose: To enter user details and shipping details into shopping cart
	 * Was unable to use .sendkeys or .clear due to some element focus issue hence had to go for Actions
	 * This meant 4-5 lines of code each time we enter data hence wrote a common function 
	 * 
	 * Since .clear() was not working . We pass the length of the data auto filled in the text box , do those many backspaces and then enter text
	 * 
	 */
	public void clearAndWriteText(WebElement e,int sizeOfTextToBeCleared,String textToBeEntered,WebDriver driver)
	{
		Actions actions = new Actions(driver);
		actions.moveToElement(e);
		actions.click();
		for(int i=0;i<sizeOfTextToBeCleared;i++)
			actions.sendKeys(Keys.BACK_SPACE);
		actions.sendKeys(textToBeEntered);
		actions.build().perform();
	}

}
