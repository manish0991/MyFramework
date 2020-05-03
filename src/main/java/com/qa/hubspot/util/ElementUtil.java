package com.qa.hubspot.util;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;



public class ElementUtil extends BasePage {
	
    WebDriver driver;
    WebDriverWait wait;
    JavaScriptUtil JsUtil ;
    Properties prop;
    
       public ElementUtil(WebDriver driver){
    	   prop =super.prop;
    	   this.driver =driver;
    	   wait = new WebDriverWait(driver, AppConstants.DEFAULT_TIME_OUT);
    	   JsUtil = new JavaScriptUtil(driver);
       }
       
       
       public boolean waitForTitlePresent(String title){
    	    wait.until(ExpectedConditions.titleIs(title));
    	   return true;
       }
       
       
       
       
       public boolean waitForElementPresent(By locator){
    	   
    	   wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    	   return true;
       }
       
       
         public boolean waitForElementVisable(By locator){
    	   
    	   wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    	   return true;
       }
       
       
       
       
       public String doPageTitle(){
    	   try{
    		  return driver.getTitle(); 
    	   }
    	   catch (Exception e)
    	   {
    		   System.out.println("Some Exception got occured while getting title");
    	   }
		return null;
       }
       
       
       
       /**
        * @author This method is used to create web element on the basic of By locator
        * @param locator
        * @return element
        */
       
       public WebElement getElement(By locator){
    	   WebElement element =null; 
    	   try{
    		//   if (waitForElementPresent(locator));
    			   
    			   element= driver.findElement(locator);   
    	           if (highlightElement){
    	        	   
    	        	   JsUtil.flash(element); ;
    	           }
    	     }
    	       catch(Exception e)
    	       {
    		   System.out.println("Some Exception got occured while creating webelement");
    	        }
    	   return element;
       }
    
       
       public void doClick (By locator){
    	   try{
    	   getElement(locator).click();
    	   }
    	   catch(Exception e)
    	   {
    		   System.out.println("Some exception got occured while clicking");
    	   }
       }
       
       
       public void doSendKeys(By locator, String value){
    	   try{
        	 WebElement ele =  getElement(locator);
        	 ele.clear();
        	 ele.sendKeys(value);
        	   }
        	   catch(Exception e)
        	   {
        		   System.out.println("Some exception got occured while entering value in a field");
        	   }
       }
          
       
       public boolean doIsDisplayed(By locator){
    	   
    	   try{
    		 return getElement(locator).isDisplayed();   
    	   }
    	   catch (Exception e)
    	   {
    		   System.out.println("Some Exception occured ");
    	   }
    	   return false;
       }
       
       public String doGetText(By locator){
    	   
    	   try{
    	 return getElement(locator).getText();
    	   }
    	   catch (Exception e)
    	   {
    		   System.out.println("Some exception occured while getting the text from an element...");
    	   }
		return null;
    	  
    
    	  
    	  
       }
       
       
       
       
       
       
       
       
       
       
       
       
       
       
    
}
