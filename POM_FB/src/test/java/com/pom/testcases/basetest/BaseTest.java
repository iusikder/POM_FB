package com.pom.testcases.basetest;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

import com.pom.util.Constants;
import com.pom.util.ExtentManager;
import com.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {	
	public ExtentReports extent = ExtentManager.getInstance();
	public ExtentTest test;
	public WebDriver driver;
	public Xls_Reader xls = new Xls_Reader(Constants.DATA_XLS_PATH);

	
	public void initialize(String bType){
		test.log(LogStatus.INFO, "Opening Browser " + bType);		
		if(bType.equals("Mozilla")){			
			//Initialize Options
			System.setProperty("webdriver.gecko.driver", Constants.GECKO_DRIVER_EXE);															 
			driver = new FirefoxDriver();					
		}else if(bType.equals("Chrome")){
			//=================================
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			
			//=======.==========================
			//Initialize Options
			System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_EXE);
			driver =new ChromeDriver(options);
			//driver = new ChromeDriver();			
		}
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();		
			test.log(LogStatus.INFO, "Browser Opended");		
	}
	
	
	//Taking Screenshot
			public void takeScreenshot(){
				Date d = new Date();
				String screenshotFile = d.toString().replace(":", " ").replace(" ", "_")+".png";
				String filePath=com.pom.util.Constants.REPORT_PATH+"screenshots//"+screenshotFile;
				//Store Screenshot in the File
				//TakeScreensot
				File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				try{
					//Get the Dynamic folder name here
					FileUtils.copyFile(srcFile, new File(filePath));			
				}catch(IOException e){
					e.printStackTrace();
				}
				test.log(LogStatus.INFO, test.addScreenCapture(filePath));			
			}
			
			
			public void reportFailure(String failureMessage){
				test.log(LogStatus.FAIL, failureMessage);
				takeScreenshot();
				Assert.fail(failureMessage);
			}
}
