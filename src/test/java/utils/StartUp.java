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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
	  String filepath=System.getProperty("user.dir")+"/src/main/java/dataRepository/";
	  ObjectWriter writer;
	  ObjectMapper mapper = new ObjectMapper();
	  String filename;

	  public StartUp()
	  {
		  System.out.println("Inside start up constructor");
		  getDriver();
		  	  
	  }
		
	  protected WebDriver getDriver(){
		  
		 if(driver==null){
			 System.out.println("incongni");
				System.setProperty("webdriver.chrome.driver",chromepath);
				ChromeOptions options = new ChromeOptions();
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				options.addArguments("-incognito");
				options.addArguments("window-size=5000x5000");
				//options.addArguments("headless");
				options.addArguments("disable-gpu");
				System.out.println("inside healdless startup");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				driver = new ChromeDriver(options);
				driver.manage().window().maximize(); 
		 }
		  
			return driver;
 
			
		}

	 

	public Map<String,Object> beforeClass(String dataFileName)
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


