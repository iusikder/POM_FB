//This code is for HF 2018

package com.pom.util;


import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {   //ReportConfig.xml holds the Report
	private static ExtentReports extent;
	
	public static ExtentReports getInstance(){
		if(extent==null){
			Date d = new Date();
			String fileName = d.toString().replace(":","_").replace(" ","_")+".html";  //Report will be generated depends on the time
			String reportPath =Constants.REPORT_PATH+fileName;
			
			extent = new ExtentReports(reportPath, true, DisplayOrder.NEWEST_FIRST);//Creates the Object of Extent Reports and give the Path where you want to generate the Report...
			extent.loadConfig(new File(System.getProperty("user.dir")+"//ReportsConfig.xml"));
			//Optional
			extent.addSystemInfo("Selenium Version", "2.53.0").addSystemInfo("Environment", "QA");
		}
		return extent;
	}
	
}

