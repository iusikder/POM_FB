package com.pom.pages.settings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.pom.base.BasePage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class SecurityLoginPage extends BasePage {
	@FindBy(xpath=Constants.CHANGE_PASSWORD_LINK)
	public WebElement change_Password_Link;
	@FindBy(xpath=Constants.OLD_PASSWORD)
	public WebElement oldPassword;
	@FindBy(xpath=Constants.NEW_PASSWORD)
	public WebElement newPassword;
	@FindBy(xpath=Constants.CONF_PASSWORD)
	public WebElement confirmPassword;
	@FindBy(xpath=Constants.SAVE_CHANGES)
	public WebElement saveChanges;
	@FindBy(xpath=Constants.REVIEW_SESSIONS_RADIO)
	public WebElement reviewSessions_Radio;
	@FindBy(xpath=Constants.CONTINUE_BUTTON)
	public WebElement continueButton;
	
	public SecurityLoginPage(WebDriver driver, ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	//If Element is present then Click on Password Changed Link, otherwise, Report Failure.
	public void gotoPasswordChange(){
		test.log(LogStatus.INFO, "Clicking on Password Change");
		if(!isElementPresent(Constants.CHANGE_PASSWORD_LINK)){
			reportFailure("Element not found " + Constants.CHANGE_PASSWORD_LINK);
		}
		change_Password_Link.click(); //Clicking on Password Link
		test.log(LogStatus.INFO, "On Password Change Page");
	}
	
	//Changing Password
	public String doPasswordChange(String oPassword, String nPassword){
		test.log(LogStatus.INFO, "Changing Password");
		oldPassword.sendKeys(oPassword);
		newPassword.sendKeys(nPassword);
		confirmPassword.sendKeys(nPassword);
		saveChanges.click();	
		
		//If "Radio Button" is not Present then it will return "Unsuccessful", otherwise, return "Success"
		if(!isElementPresent(Constants.REVIEW_SESSIONS_RADIO))
			return "Unsuccessful";				
			Actions actions = new Actions(driver); 
			actions.click(reviewSessions_Radio).perform();
				
			Actions actionsPC = new Actions(driver); 
			actionsPC.click(continueButton).perform();
			test.log(LogStatus.INFO, "Password Changed Successfully");
			return "Success";
	}
}
