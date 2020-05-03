package com.qa.hubspot.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.util.AppConstants;
import com.qa.hubspot.util.ElementUtil;
import com.qa.hubspot.util.JavaScriptUtil;

public class HomePage extends BasePage{
	
	WebDriver driver;
	ElementUtil elementutil;
	

	By header = By.cssSelector("h1.private-page__title");
	By accountname = By.cssSelector("span.account-name");
	
	By mainContactsLink = By.id("nav-primary-contacts-branch");
	By childContactsLink = By.id("nav-secondary-contacts");
	
	public HomePage(WebDriver driver){
		  this.driver=driver;
		  elementutil = new ElementUtil(driver);
	}
	
	
	public String getHomePageTitle(){
		elementutil.waitForTitlePresent(AppConstants.HOME_PAGE_TITLE);
		return elementutil.doPageTitle();
		
	}
	
	public String getHomePageHeader(){
		   return elementutil.doGetText(header);
	}
	
	
	public String getUserLoggedInAccountName(){
		
		return elementutil.doGetText(accountname);
	}
	
	
	public void clickOnContacts() {
		elementutil.waitForElementPresent(mainContactsLink);
		elementutil.doClick(mainContactsLink);
		
		elementutil.waitForElementPresent(childContactsLink);
		elementutil.doClick(childContactsLink);
		
	}

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

	
	
	
	
	
	
	
}
