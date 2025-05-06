package com.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.utils.DriverManager;

public class BaseTest {

	@BeforeMethod
	public void setup() {
		DriverManager.initializeDriver();
	}
	
	@AfterMethod
	public void tearDown() {
		PageDriver.INSTANCE.quitDriver();
	}
}
