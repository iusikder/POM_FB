package com.pom.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pom.pages.LandingPage;
import com.pom.pages.LaunchPage;
import com.pom.pages.LoginPage;
import com.pom.pages.ProfilePage;
import com.pom.testcases.basetest.BaseTest;
import com.pom.util.Constants;
import com.pom.util.DataUtil;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileTest extends BaseTest {
	String testCaseName="ProfileTest";
	
	@Test(dataProvider="getData")
	public void profileTest(Hashtable<String,String> data){
		test = extent.startTest(testCaseName);
		if(!DataUtil.isTestExecutable(xls, testCaseName)|| data.get(Constants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test as Runmode is N");
			throw new SkipException ("Skipping the test as Runmode is N");
		}	
		
		test=extent.startTest("Profile Test");
		test.log(LogStatus.INFO, "Starting Profile Test");
		
		initialize(data.get("Browser"));				
		//LaunchPage launchPage = PageFactory.initElements(driver, LaunchPage.class);
		LaunchPage launchPage = new LaunchPage(driver,test);
		LoginPage loginPage = launchPage.gotoLoginPage();
	//	loginPage.verifyTitle("Facebook Login");
		Object page = loginPage.doLogin(data.get("Username"), data.get("Password"));
		
		//verify Login
		if(page instanceof LoginPage)
			Assert.fail("Login Failed");
		else if(page instanceof LandingPage)
			System.out.println("Logged in successfully");
	
		
		
		//Starting profile Test from here......
		LandingPage landingPage=(LandingPage)page;
		//landingPage.getMenu().search();
		//landingPage.verifyTitle("XXXXXX");
		
		ProfilePage profilePage = landingPage.gotoProfilePage();
		
		
		
		
		//Verifying Profile Page
		String actualResult = profilePage.verifyProfile();
		test.log(LogStatus.INFO, "Actual Profile Page Name : " + actualResult);
		
		if(!actualResult.equals(data.get("ExpectedResult")))
			reportFailure("Got Profile Page Name as : "+ actualResult);
		test.log(LogStatus.PASS, "Test Passed");		

		//profilePage.getMenu().logOut();
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
