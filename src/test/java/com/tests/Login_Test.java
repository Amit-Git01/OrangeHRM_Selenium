package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.models.DashboardPage;
import com.models.LoginPage;

public class Login_Test extends BaseTest{

	@Test
	public void test_login_with_valid_credentials() throws InterruptedException {
		
		LoginPage loginPage = new LoginPage();
		
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		DashboardPage dash  = loginPage.login("Admin", "admin123");
		dash.verify_dashboardPage_isDisplayed();
		dash.logout();
		
	}
	
	@Test
	public void test_login_with_invalid_credentials() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		loginPage.login("Admin", "admin1234");
		loginPage.verify_error_msg();
		
	}
	
	@Test(enabled = true)
	public void test_login_without_credentials() {
		
		LoginPage loginPage = new LoginPage();
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		loginPage.login("", "");
		loginPage.verify_required_text();
		
	}
}
