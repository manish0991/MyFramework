package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public  WebDriver driver;
	public  Properties prop;
	public static boolean highlightElement;
	public OptionsManager optionsManager;
	  
	
	public static ThreadLocal <WebDriver> tldriver = new ThreadLocal <WebDriver>();
	public static synchronized WebDriver getDriver(){
		return tldriver.get();
	}
	
	
	   public WebDriver init_driver(String browserName){
		   
		    highlightElement = prop.getProperty("highlight").equals("yes") ? true : false ;
		    System.out.println("Browser name is " + browserName);
		    optionsManager = new OptionsManager(prop);
		   
		   if (browserName.equals("chrome"))
		        {
			   
			
			     WebDriverManager.chromedriver().setup();
			   //  driver = new ChromeDriver(optionsManager.getChromeOptions());
			     tldriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			     
//			     if (prop.getProperty("headless").equals("yes")){
//			     ChromeOptions co = new ChromeOptions();
//			     co.addArguments("--headless");
//			     driver= new ChromeDriver(co);
//		        }
//		   else
//		   {      driver = new ChromeDriver();
//			     }
		   
	   }
		   else if (browserName.equals("firefox"))
		   {
			   WebDriverManager.firefoxdriver().setup();
			            // driver = new FirefoxDriver();
		   } 
		   else 
		   {
			   System.out.println("Browser name" + browserName + " is not found, Please pass correct browser");
		   }
		   
		   getDriver().manage().deleteAllCookies();
		   getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		   getDriver().manage().window().maximize();
		  // driver.get(url);
		   
		    return getDriver();
	   }
	   
	   
	   //FileInputStream -> make a connection or stream with config.properties 
	   //prop.load () -> to load all properties 
	   
	        public Properties init_properties(){
	        	
	        	 prop  = new Properties();
	        	 String path = null;
	     		 String env = null;

	     		try {
	     			env = System.getProperty("env");

	     			if (env.equals("qa")) {
	     				path = "./src/main/java/com/qa/hubspot/config/qa.config.properties";
	     			} else if (env.equals("dev")) {
	     				path = "./src/main/java/com/qa/hubspot/config/dev.config.properties";
	     			}
	     		} catch (Exception e) {
	     			path = "./src/main/java/com/qa/hubspot/config/config.properties";
	     		}
	        	  
	        	 try {
					FileInputStream ip = new FileInputStream(path);
					prop.load(ip);
				} catch (FileNotFoundException e) {
					System.out.println("Some Issue with config properties");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	    return prop;
	        	 
	        	 
	        	 
	        	 
	        }
	
	
	public String getScreenshot(){
		
		File src =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+ "/screenshots" + System.currentTimeMillis()+ ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Screenshot captured is failed");
		}
		return path;
	}
	
	


}
