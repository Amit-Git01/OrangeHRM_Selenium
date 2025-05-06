package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener{
	
	private static final Logger LOGGER = LogManager.getLogger(Listener.class);
	
	public void onTestStart(ITestResult result) {
		
		LOGGER.info("Test is started : {}",result.getName());
	    
	  }

	public void onTestSuccess(ITestResult result) {
		
		LOGGER.info("Test is Success : {}",result.getName());
	    
	  }
	
	public void onTestFailure(ITestResult result) {
		
		ScreenshotUtils.takesScreenshot(result.getName());
		LOGGER.info("Test is failed : {}",result.getName());
	    
	  }

}