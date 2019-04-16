package com.pom.base;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//Dated: December- 25- 2018.
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.pom.pages.common.TopMenu;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class BasePage {
	
	public WebDriver driver;
	public TopMenu menu;
	public ExtentTest test;
	
	
	public BasePage(){} //Default Constructor
	
	public BasePage(WebDriver driver, ExtentTest test){  //All the Constructors of Page Classes will be calling this Constructor.
		this.driver=driver;
		this.test=test;
		menu=new TopMenu(driver,test);
		PageFactory.initElements(driver, menu);
	}	
	
	
	
	public String verifyTitle(String expTitle){
		test.log(LogStatus.INFO, "Verifying the Title " + expTitle);
		//WebDriver code
		
		return "";
	}
	
	public String verifyText(String locator, String expText){
		return "";
	}
	
	public boolean isElementPresent(String locator){
		test.log(LogStatus.INFO, "Trying to find element - " + locator);
		int s = driver.findElements(By.xpath(locator)).size();
		if(s==0){
			test.log(LogStatus.INFO, "Element Not Found ");
			return false;
		}
		else{
			test.log(LogStatus.INFO, "Element Found ");
			return true;
		}
	}
	
	public TopMenu getMenu(){
		return menu;
	}
	
	//Taking Screenshot
		public void takeScreenshot(){
			Date d = new Date();
			String screenshotFile = d.toString().replace(":", " ").replace(" ", "_")+".png";
			String filePath=com.pom.util.Constants.REPORT_PATH+"screenshots//"+screenshotFile;
			//Store Screenshot in the File
			//TakeScreensot
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			try{
				//Get the Dynamic folder name here
				FileUtils.copyFile(srcFile, new File(filePath));			
			}catch(IOException e){
				e.printStackTrace();
			}
			test.log(LogStatus.INFO, test.addScreenCapture(filePath));			
		}
		
		
		public void reportFailure(String failureMessage){
			test.log(LogStatus.FAIL, failureMessage); //I have to check failureMessage here...
			takeScreenshot();
			Assert.fail(failureMessage);
		}

	
	
	
}