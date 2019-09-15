package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;

	public class ObjectFactory 
	{
	  Properties properties;

	  public ObjectFactory(String mapFile) 
	  {
		  properties = new Properties();
	      try 
	      {
	    	  FileInputStream Master = new FileInputStream(mapFile);
	    	  properties.load(Master);
	    	  Master.close();
	      }
	      catch(IOException e) 
	      {
	           System.out.println(e.getMessage());
	      }
	  }
	  
	  /*
	   * Purpose: This function take input from .properties files from uimap. 
	   * It splits the input which is in the form "xpath-->//" to locatorType  and locatorValue. 
	   * The function  returns value of type By. 
	   * This class enables the user to store any type of locator in the properties file.
	   */

	 public By getLocator(String ElementName) throws Exception 
	 {

		String locator = properties.getProperty(ElementName);
        String locatorType = locator.split("-->")[0];
        String locatorValue = locator.split("-->")[1];
          if(locatorType.toLowerCase().equals("id"))
                return By.id(locatorValue);
          else if(locatorType.toLowerCase().equals("name"))
                return By.name(locatorValue);
          else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
                return By.className(locatorValue);
          else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
                return By.className(locatorValue);
          else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
                return By.linkText(locatorValue);
          else if(locatorType.toLowerCase().equals("partiallinktext"))
                return By.partialLinkText(locatorValue);
          else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
                return By.cssSelector(locatorValue);
          else if(locatorType.toLowerCase().equals("xpath"))
          {	
        	 // System.out.println("locatorValue="+locatorValue);
                return By.xpath(locatorValue);
          }
          else
                  throw new Exception("Locator type '" + locatorType + "' not defined!!");
      }


		 
}
