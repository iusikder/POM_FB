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
import com.pom.testcases.basetest.BaseTest;
import com.pom.util.Constants;
import com.pom.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest{
	String testCaseName="LoginTest";

	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String> data){
		test = extent.startTest(testCaseName);
		if(!DataUtil.isTestExecutable(xls, testCaseName)|| data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException ("Skipping the test as Runmode is N");
		}		
		
		test.log(LogStatus.INFO, "Starting Login Test");
		test.log(LogStatus.INFO, "Opening Browser");			
		initialize(data.get("Browser"));	
		
		LaunchPage launchPage = new LaunchPage(driver, test);
		PageFactory.initElements(driver, launchPage);			
		LoginPage loginPage = launchPage.gotoLoginPage();
		//loginPage.takeScreenshot();
		test.log(LogStatus.INFO, "Logging in");
		Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));		
		
		String actualResult = "";
		//If I am logged in
		if(page instanceof LandingPage)
			actualResult = "Success";
		else		
			actualResult="Unsuccessful";	
		
		if(!actualResult.equals(data.get("ExpectedResult"))){
			reportFailure("Login Not Successful"); //I have to check here also.....	
		}
		test.log(LogStatus.PASS, "Login Test Passed");		// NEXT TIME I HAVE TO CHECK HERE	
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

