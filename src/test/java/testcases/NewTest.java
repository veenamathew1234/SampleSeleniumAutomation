package testcases;

import org.testng.annotations.Test;

import utils.StartUp;

public class NewTest extends StartUp {
  @Test
  public void launchGoogle() {
	  driver.get("https://www.google.com/");
	 
	  
  }
}
