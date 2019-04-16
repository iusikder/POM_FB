package com.pom.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pom.base.BasePage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BasePage {
	
	@FindBy(xpath=Constants.LOGIN_USERNAME)
	public WebElement userName;
	@FindBy(xpath=Constants.LOGIN_PASSWORD)
	public WebElement password;	
	
	public LoginPage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	
	
	
	public Object doLogin(String uName, String pWard){
		test.log(LogStatus.INFO, "Logging in -"+ uName+"/"+pWard);		
		userName.sendKeys(uName);
		password.sendKeys(pWard);	
		password.sendKeys(Keys.ENTER);
		
		//Logic/Validate
		boolean loginSuccess = isElementPresent(Constants.PROFILEPAGE_LINK);		
		if(loginSuccess){
			test.log(LogStatus.INFO, "Login Success");
			LandingPage landingPage = new LandingPage(driver,test);	
			PageFactory.initElements(driver, landingPage);
			return landingPage;
		}
		else{
			test.log(LogStatus.FAIL, "Login Not Succcess");
			LoginPage loginPage = new LoginPage(driver,test);
			PageFactory.initElements(driver, loginPage);
			return loginPage;
		}
	}
	
	
	/*
	public void verifyTitle(String expTitle){
		
	}*/
}
