package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.Credentials;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class LoginPage extends BasePage {
	
	    WebDriver driver;
	    ElementUtil elementutil;
	    JavaScriptUtil JsUtil ;
	
	    By emailId = By.id("username");
	    By pwd = By.id("password");
	    By loginButton = By.id("loginBtn");
	    By SignUpLink = By.linkText("Sign up");
	    By loginErrorText =By.xpath("//div[@class='private-alert__inner']");
	
	    
	    public LoginPage(WebDriver driver){
	    	   this.driver=driver;
	    	   elementutil=new ElementUtil(driver);
	    	   JsUtil = new JavaScriptUtil(driver);
	    	   }
	   
	    //Page Actions 
	    
	    public String getPageTitle(){
	    	elementutil.waitForTitlePresent(AppConstants.LOGIN_PAGE_TITLE);
	    	  return elementutil.doPageTitle();
	    }
	    
	    public String getPageTitleUsingJS(){
	    	return JsUtil.getTitleByJS();
	    
	    }
	    
	
	    
	    public boolean checkSignUplink(){
	    	elementutil.waitForElementPresent(SignUpLink);
	    	return  elementutil.doIsDisplayed(SignUpLink);
	    }
	
	    
	    public HomePage login (Credentials userCred){
	    	       elementutil.doSendKeys(emailId, userCred.getAppUsername());
	    	       elementutil.doSendKeys(pwd, userCred.getAppPassword());
	    	       elementutil.doClick(loginButton);  
	    	        return new HomePage(driver);
	    	        
	    }
	
	
	public boolean checkLoginErrorMsg(){
		  
	      return elementutil.doIsDisplayed(loginErrorText);
	  }
	

}
