package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class HomePageTest {
	
	WebDriver driver;
    BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	HomePage homepage;
	Credentials userCred;
	
	
	@BeforeTest 
	public void  setup() throws InterruptedException{
		
	   basepage = new BasePage();
	   prop = basepage.init_properties();
	   String browserName = prop.getProperty("browser");
	   driver = basepage.init_driver(browserName);
	   driver.get(prop.getProperty("url"));
	   loginpage = new LoginPage(driver);
	   userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	   homepage =  loginpage.login(userCred);
	    
	   // Thread.sleep(5000);
	}
	 @Test(priority=1)
	public void HomePageTitleTest(){
		String title =homepage.getHomePageTitle();
		System.out.println("HomePage title is" + title);
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	 
	 @Test(priority=2)
	public void verifyHomePageHeaderTest(){
		  String header=homepage.getHomePageHeader();
		  System.out.println("HomePage header is" +header);
		  Assert.assertEquals(header, AppConstants.HOME_PAGE_HEADER);
	}
	
	 @Test(priority=3 , enabled=false)
	public void verifyLoggedInUserTest(){
		   String accountname =homepage.getUserLoggedInAccountName();
		   System.out.println("Loged User name is" + accountname);
		   Assert.assertEquals(accountname, prop.getProperty(accountname));
	}
	
	
	
	
	
	
	
	
	
	
	

	@AfterTest
	public void tearDown(){
		driver.close();
	}
	
	
	
	
	
	
	
	
	

}
