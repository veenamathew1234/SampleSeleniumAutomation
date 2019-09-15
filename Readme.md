Readme.md

The problem was to write 2 scripts for checking a successful Payment and failed payment

Approach : Since 2 scripts had to be created . I created different test data for each test case.
The test data contains  the buyer details and shipping info along with their credit card details.

 Most of the page I am accessing in the website is split into each java page , where functions of that page is written. 
Below is the project structure

-pages
	Package hold the java classes for each webpage.
		-HomePage.java
		-PaymentMethod.java
		-ShoppingCart.java

-uimap
	This package contains locators of webelement for each of the webpage
		-HomePage.properties
		-PaymentMethod.properties
		-ShoppingCart.properties

-utils
	This package contains come general and utility classes thats frequently used across the project
		-CommonMethods.java
			Methods that are frequently used in almost all java classes can be written in this file.
			So far we have written a function to add text to text box. We had to write this function as .sendkeys() was
			not working and we had to opt for Actions methods which added 5-6 lines of code everytime we needed to enter text.
		-ObjectFactory.java
			This class take input from .properties files from uimap. It splits the input which is in the form "xpath-->//" to locatorType  and locatorValue. The function in the class returns value of type By. This class enables the user to store any type of locator in the properties file.

		-StartUp.java
			This class is for setting up for browser . We have done set up for both Chrome and firefox.
			Class also contains a function to load the test data when the file name is passed.

	-testcases
		TC_SuccessfulPayment.java
		TC_FailedPayment.java

How to run the project
------------------------

I am attaching the entire project repository to the drive. I am sending link to the folder "Veena Mathew". I have placed the project folder "Gojek Assignment" under the folder "Veena Mathew" . "Gojek Assignment is the main project folder that you need to export to eclipse. Since its a maven project, there is no need to add an jar files manually. I have mentioned all of them in the dependencies

Ways to run the project
--------------
Once you import the project to eclipse. You can choose anyone of the methods to run the project.
1)Right click on the testng.xl and Run As TestNG Suit
2)Running though Command line : go to the project location root in commandline and run the following command 
	mvn clean install 
	This will automatically run the suite

Report
------
The latest report of the run can be found under the folder test-output. Name of the report : emailable-report.html. You can open the report using the option "Open with " Web Browser 

Cross Browser Compatibility
-----------------------

Implementation for cross browser running has been done in the porject.Currently cross browser is implemented for Firefox and Chrome . The testng.xml currently contains both the test cases with browser parameter = Chrome. Inorder to run in Firefox, you just need to change the browser parameter="FireFox"