package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class StartUp {
	
	  protected static WebDriver driver;
	  protected static Map<String,Object> DataObj;
	  String chromepath=System.getProperty("user.dir")+"/chromedriver";
	  String firefoxpath=System.getProperty("user.dir")+"/geckodriver";
	  String filepath=System.getProperty("user.dir")+"/src/test/java/testdata/";
	  ObjectWriter writer;
	  ObjectMapper mapper = new ObjectMapper();
	  String filename;

	  public WebDriver fetchDriver(String browser)
	  {
		  System.out.println("Inside fetch");
		  switch(browser)
		  {
		  case "Chrome":
			  if(driver==null){
					 System.out.println("incongni");
						System.setProperty("webdriver.chrome.driver",chromepath);
						ChromeOptions options = new ChromeOptions();
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
						options.addArguments("-incognito");
						capabilities.setCapability(ChromeOptions.CAPABILITY, options);
						driver = new ChromeDriver(options);
						driver.manage().window().maximize(); 
				 }
			  break;
			  
		  case "Firefox":
			  if(driver==null)
			  {
				  System.setProperty("webdriver.gecko.driver",firefoxpath);
				  driver = new FirefoxDriver();
				  driver.manage().window().maximize();
			  }
			  break;
		  }
		  
		  return driver;
	  }

	 

	public Map<String,Object> loadJsonData(String dataFileName)
	{
		
		try 
		{
			ObjectMapper mapperForWrite = new ObjectMapper();
			writer = mapperForWrite.writer(new DefaultPrettyPrinter());
			JsonFactory fUser = new JsonFactory();
			JsonParser jp1;
			jp1 = fUser.createParser(new File(filepath + dataFileName));
			jp1.nextToken();
			DataObj = mapperForWrite.readValue(jp1, Map.class);
		}
			
			catch (IOException e) 
		{
				e.printStackTrace();
		}
		return DataObj;
	}
	

	

}


