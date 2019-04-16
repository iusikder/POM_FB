package com.pom.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.pom.base.BasePage;
import com.pom.util.Constants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ProfilePage extends BasePage {
	@FindBy(xpath=Constants.VERIFY_PROFILENAME)
	public WebElement verifyProfileName;
	
	public ProfilePage(WebDriver driver, ExtentTest test){
		super(driver,test);
	}
	
	
	public String verifyProfile(){
		test.log(LogStatus.INFO, "Verifying Profile");
		if(!isElementPresent(Constants.VERIFY_PROFILENAME))
			return "Unsuccessful";			
				
			test.log(LogStatus.INFO, "Profile Page Opended Successfully");
			return "Success";
	}
	
	/*public void verifyTitle(String expTitle){
		
	}*/
}
