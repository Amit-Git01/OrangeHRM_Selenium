package com.models;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LoginPage extends BaseModel{
	
	private static final Logger LOGGER = LogManager.getLogger(LoginPage.class);

	@FindBy(xpath = "//h5[text()='Login']")
	private WebElement loginTitle;
	
	@FindBy(name = "username")
	private WebElement username_field;
	
	@FindBy(name = "password")
	private WebElement password_field;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement login_btn;
	
	@FindBy(xpath = "//p[contains(.,'Forgot your password?')]")
	private WebElement forgot_password_link;
	
	@FindBy(xpath = "//p[text()='Invalid credentials']")
	private WebElement invalid_text;
	
	@FindBy(xpath = "//span[contains(., 'Required')]")
	private List<WebElement> required_text;
	
	public LoginPage enter_username(String username) {
		type(username_field, username);
		LOGGER.info("Entered username: {}", username);
		return this;
	}
	
	public LoginPage enter_password(String password) {
		type(password_field, password);
		LOGGER.info("Entered password");
		return this;
	}
	
	public DashboardPage click_login_btn() {
		click(login_btn);
		LOGGER.info("Clicked on login button");
		return new DashboardPage();
	}
	
	public DashboardPage login(String username, String password) {
		enter_username(username);
		enter_password(password);
		return click_login_btn();
	}
	
	public void verify_page_title() {
		String heading = getText(loginTitle).strip();
		Assert.assertEquals(heading, "Login", "Heading did not matched...");
		LOGGER.info("Login heading displayed : {}", heading);
	}
	
	public void verify_error_msg() {
		String msg = getText(invalid_text).strip();
		Assert.assertEquals(msg, "Invalid credentials", "Text did not matched...");
		LOGGER.info("Error message is displayed : {}", msg);
	}
	
	public void verify_forget_link() {
		Assert.assertTrue(isDisplayed(forgot_password_link));
		LOGGER.info("Forgot link is displayed : {}", getText(forgot_password_link).strip());
	}
	
	public void verify_required_text() {
		for(WebElement element : required_text) {
			Assert.assertEquals(element.getText().strip(), "Required", "Text did not matched...");
		}
	}
	
}
