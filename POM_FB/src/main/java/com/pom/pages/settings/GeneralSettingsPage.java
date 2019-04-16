package com.pom.pages.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pom.base.BasePage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GeneralSettingsPage extends BasePage {
	@FindBy(xpath=Constants.SEC_LOGIN_LINK)
	public WebElement sec_LoginLink;	
	
	public GeneralSettingsPage(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	
	
	
	public SecurityLoginPage gotoSecurityLoginPage(){
		test.log(LogStatus.INFO, "Going to Secutiry and Login Page");
		sec_LoginLink.click();			

		SecurityLoginPage secLogin = new SecurityLoginPage(driver,test);
		PageFactory.initElements(driver, secLogin);
		return secLogin;
	}
	
	
}
