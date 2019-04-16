package com.pom.util;

public class Constants {

	//Paths
	public static final String CHROME_DRIVER_EXE = "C:\\AllDrivers\\LatestChrome\\chromedriver.exe";
	public static final String GECKO_DRIVER_EXE = "C:\\AllDrivers\\NewGeckoDriver\\geckodriver.exe";
	
	//Locators
	public static final String PROFILEPAGE_LINK = "//span[@class='_1vp5']";
	public static final String LOGIN_USERNAME = "//input[@id='email']";
	public static final String LOGIN_PASSWORD = "//input[@id='pass']";
	public static final String NAVIGATION_LABEL = "//div[@id='userNavigationLabel']";
	public static final String SEC_LOGIN_LINK = "//a[@title='Security and Login']";
	public static final String CHANGE_PASSWORD_LINK = "//span[text()='Change password']";
	public static final String SETTINGS_LINK = "//span[text()='Settings']";
	public static final String OLD_PASSWORD = "//input[@id='password_old']";
	public static final String NEW_PASSWORD = "//input[@id='password_new']";
	public static final String CONF_PASSWORD = "//input[@id='password_confirm']";
	public static final String SAVE_CHANGES = "//input[@value='Save Changes']";
	public static final String REVIEW_SESSIONS_RADIO= "//input[@value='review_sessions']";
	public static final String CONTINUE_BUTTON = "//button[@type='submit']";
	public static final String VERIFY_PROFILENAME = "//a[contains(text(),'Istiaq Ahme')]"; //DELETED

	
	//URLs
	public static final String LOGINPAGE_URL = "http://facebook.com";
	
	// Report Path
	//public static final String REPORT_PATH = "C:\\ExtentReport\\Report\\";
	//public static final String REPORT_PATH = System.getProperty("user.dir")+"/test-output/ExtentReport.html";
	public static final String REPORT_PATH = System.getProperty("user.dir")+"/test-output/Extent.Facebook.html_";
	
	//Reading Data 
	public static final String DATA_XLS_PATH = System.getProperty("user.dir")+"\\src\\main\\java\\com\\pom\\testdata\\Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";						
	public static final String TESTCASES_SHEET = "TestCases";
	public static final String RUNMODE_COL = "Runmode";

	
	
	

	
	

	
}
