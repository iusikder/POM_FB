package com.pom.pages.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pom.pages.settings.GeneralSettingsPage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TopMenu {
	@FindBy(xpath=Constants.NAVIGATION_LABEL)
	public WebElement navigationLabel;	
	@FindBy(xpath=Constants.SETTINGS_LINK)
	public WebElement settings_Link;
	
	ExtentTest test;
	WebDriver driver;
	
	//Constructor
	public TopMenu(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	
	
	//Methods
	public void logOut(){
		
	}
	
	public GeneralSettingsPage gotoSettings(){
		test.log(LogStatus.INFO,"Going to Settings");
		navigationLabel.click();
		settings_Link.click();
		test.log(LogStatus.INFO, "Settings Page Opened");		
		
		GeneralSettingsPage settings = new GeneralSettingsPage(driver,test);
		PageFactory.initElements(driver, settings);
		return settings;
	}
	
	
	public void search(){
		
	}
}








