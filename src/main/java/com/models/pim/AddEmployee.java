package com.models.pim;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class AddEmployee extends PIMPage {
	
	private static final Logger LOGGER = LogManager.getLogger(AddEmployee.class);

	public AddEmployee() {
		super();
	}

	@FindBy(css = ".orangehrm-main-title")
	private WebElement add_employee_heading;

	@FindBy(name = "firstName")
	private WebElement first_name_field;

	@FindBy(name = "middleName")
	private WebElement middle_name_field;

	@FindBy(name = "lastName")
	private WebElement last_name_field;

	@FindBy(xpath = "(//label[normalize-space(text())='Employee Id']/following::input)[1]")
	private WebElement emp_id_field;

	@FindBy(xpath = "//input[@type='checkbox']/following-sibling::span[1]")
	private WebElement login_toggle_btn;

	@FindBy(xpath = "(//label[normalize-space(text())='Username']/following::input)[1]")
	private WebElement username_field;

	@FindBy(xpath = "//input[@type='password']")
	private List<WebElement> password_field;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement save_btn;

	@FindBy(xpath = "//span[contains(., 'Required')]")
	private List<WebElement> required_text;

	@FindBy(xpath = "//div[@class='oxd-radio-wrapper']//label")
	private List<WebElement> radio_btns;
	
	public void is_add_emp_displayed() {
		
		if (isDisplayed(add_employee_heading)) {
			String heading = getText(add_employee_heading).strip();
			Assert.assertEquals(heading, "Add Employee",
					"Assertion failed : " + getText(add_employee_heading));
			LOGGER.info("Dashboard heading is displayed: {}", heading);
			
		}
	}

	public AddEmployee enter_fname(String fname) {
		type(first_name_field, fname);
		LOGGER.info("Entered first name: {}", fname);
		return this;
	}

	public AddEmployee enter_mname(String mname) {
		type(middle_name_field, mname);
		LOGGER.info("Entered middle name: {}", mname);
		return this;
	}

	public AddEmployee enter_lname(String lname) {
		type(last_name_field, lname);
		LOGGER.info("Entered last name: {}", lname);
		return this;
	}

	public AddEmployee enter_emp_id(String empid) {
		type(emp_id_field, empid);
		LOGGER.info("Entered empid: {}", empid);
		return this;
	}

	public void click_toggle() {
		click(login_toggle_btn);
		LOGGER.info("Clicked on toggle");
	}

	public AddEmployee enter_username(String username) {
		type(username_field, username);
		LOGGER.info("Entered username: {}", username);
		return this;
	}

	public AddEmployee enter_password(String password) {

		for (WebElement element : password_field) {
			type(element, password);
		}
		return this;
	}

	public void click_save_btn() {
		click(save_btn);
		LOGGER.info("Clicked on save button");
	}

	public void check_enable_radio() {

		for (WebElement element : radio_btns) {

			if (getText(element).strip().equalsIgnoreCase("Enabled")) {
				click(element);
			}
		}
	}

}
