package com.tests;

import org.testng.annotations.Test;

import com.base.BaseTest;
import com.models.DashboardPage;
import com.models.LoginPage;
import com.models.pim.AddEmployee;
import com.models.pim.PIMPage;

public class Pim_Test extends BaseTest {

	@Test
	public void test_pim() {

		LoginPage loginPage = new LoginPage();
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		DashboardPage dash = loginPage.login("Admin", "admin123");
		dash.verify_dashboardPage_isDisplayed();
		PIMPage pim = dash.clickOnPIM();
		pim.verify_pim_page();
		pim.clickOnAddEmployee();
		
	}
	
	@Test
	public void test_pim_fail() {

		LoginPage loginPage = new LoginPage();
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		DashboardPage dash = loginPage.login("Admin", "admin123");
		dash.verify_dashboardPage_isDisplayed();
		PIMPage pim = dash.clickOnPIM();
		pim.verify_title("Tim");
		pim.clickOnAddEmployee();
		
	}
	
	@Test
	public void test_pim_add_emp() {
		LoginPage loginPage = new LoginPage();
		loginPage.verify_page_title();
		loginPage.verify_forget_link();
		DashboardPage dash = loginPage.login("Admin", "admin123");
		dash.verify_dashboardPage_isDisplayed();
		PIMPage pim = dash.clickOnPIM();
		pim.verify_pim_page();
		
		AddEmployee emp = pim.clickOnAddEmployee();

		emp.is_add_emp_displayed();
		emp.enter_fname("Amd");
		emp.enter_lname("sam");
		emp.enter_emp_id("12390");
		emp.click_toggle();
		emp.check_enable_radio();
		emp.enter_username("reamt");
		emp.enter_password("qwerty1");

	}
}
