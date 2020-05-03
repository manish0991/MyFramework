package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.page.HomePage;
import com.qa.hubspot.page.LoginPage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;

public class LoginPageTest {
	WebDriver driver;
    BasePage basepage;
	Properties prop;
	LoginPage loginpage;
	Credentials userCred;
	
	
	@BeforeTest 
	public void  setup(){
		
	   basepage = new BasePage();
	   prop = basepage.init_properties();
	   String browserName = prop.getProperty("browser");
	   driver = basepage.init_driver(browserName);
	   driver.get(prop.getProperty("url"));
	   loginpage = new LoginPage(driver);
	   userCred = new Credentials(prop.getProperty("username"), prop.getProperty("password"));
	}
	 
	@Test (priority=1)
	public void verifyLoginPageTitleTest() throws InterruptedException{
		
		//Thread.sleep(5000);
		String title = loginpage.getPageTitle();
		System.out.println("Login Page Title is" + title);
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE);	
	}
	
	@Test (priority=2)
	public void verifySignUpLink(){
		Assert.assertTrue(loginpage.checkSignUplink());	
	}
	 
	@Test (priority=3)
	public void loginTest(){
	HomePage homepage =	loginpage.login(userCred);
		String title =homepage.getHomePageTitle();
		Assert.assertEquals(title, AppConstants.HOME_PAGE_TITLE);
	}
	
	@DataProvider
	public Object [][] getLoginInvalidData(){
		
		Object data [][]= {  {"test@gmail.com", "test123"},
		                     {"test@gmail.com", ""},
		                      {"", "test@test"},
		                       {"test", "test@test"}
	
		                        
		                     };
		      return data;  
		
	}
	
	
	@Test (priority=4, dataProvider="getLoginInvalidData", enabled=false)
	public void invalidTestCase(String username, String password){
		
		userCred.setAppUsername(username);
		userCred.setAppPassword(password);
		loginpage.login(userCred);
		Assert.assertTrue(loginpage.checkLoginErrorMsg());
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.close();
	}
	
	
}
