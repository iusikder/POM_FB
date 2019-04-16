package com.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.pom.base.BasePage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LaunchPage extends BasePage {
	
	public LaunchPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}

	
	public LoginPage gotoLoginPage(){
		test.log(LogStatus.INFO, "Opening the Url - " + Constants.LOGINPAGE_URL);
		//driver.get(Constants.LOGINPAGE_URL);
		driver.get(Constants.LOGINPAGE_URL);
		test.log(LogStatus.PASS, "URL opended " + Constants.LOGINPAGE_URL);
		
		LoginPage loginPage = new LoginPage(driver,test);
		PageFactory.initElements(driver, loginPage);
		return loginPage;
		
		///return PageFactory.initElements(driver, LoginPage.class);
	}
}