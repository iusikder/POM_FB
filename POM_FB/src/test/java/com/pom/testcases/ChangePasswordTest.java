package com.pom.testcases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pom.pages.LandingPage;
import com.pom.pages.LaunchPage;
import com.pom.pages.LoginPage;
import com.pom.pages.settings.GeneralSettingsPage;
import com.pom.pages.settings.SecurityLoginPage;
import com.pom.testcases.basetest.BaseTest;
import com.pom.util.Constants;
import com.pom.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;



public class ChangePasswordTest extends BaseTest{
	String testCaseName = "ChangePasswordTest";						 
	
	@Test(dataProvider="getData")
	public void changePasswordTest(Hashtable<String,String> data){
		test = extent.startTest(testCaseName);
		
		if(!DataUtil.isTestExecutable(xls, testCaseName) || data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException ("Skipping the test as Runmode is N");
		}	
		
		
		test.log(LogStatus.INFO, "Starting Test");
		initialize(data.get("Browser"));		
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);		
		LoginPage loginPage = launchPage.gotoLoginPage();
		test.log(LogStatus.INFO, "Logging in");
		//Object page = loginPage.doLogin(data.get("Username"), data.get("OldPassword"));
		Object page = loginPage.doLogin(data.get("Username"), data.get("OldPassword"));
		
		//Validate Login
		if(page instanceof LoginPage)
			reportFailure("Could not Login");
			
		
		//Change Password
		LandingPage landingPage = (LandingPage)page;
		GeneralSettingsPage generalSettings = landingPage.getMenu().gotoSettings();
		SecurityLoginPage securityLoginPage = generalSettings.gotoSecurityLoginPage();
		securityLoginPage.gotoPasswordChange();
		String actualResult = securityLoginPage.doPasswordChange(data.get("OldPassword"), data.get("NewPassword"));
		test.log(LogStatus.INFO, "Result of Changing Password is : " + actualResult);
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure("Got Password Change result as : "+ actualResult);
		test.log(LogStatus.PASS, "Test Passed");
	}
	
	@AfterMethod
	public void quit(){
		if(extent!=null){
			extent.endTest(test);
			extent.flush();
		}
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);		
	}
}

