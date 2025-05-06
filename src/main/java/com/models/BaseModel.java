package com.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.base.PageDriver;
import com.utils.WaitUtils;

public class BaseModel {

	protected WebDriver driver;
	private static final Logger LOGGER = LogManager.getLogger(BaseModel.class);

	public BaseModel() {
		this.driver = PageDriver.getCurrentDriver();
		PageFactory.initElements(driver, this);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	protected void click(WebElement element) {
		try {
			WaitUtils.waitForElementClickable(element);
			element.click();
			WaitUtils.waitForPageLoad();
			LOGGER.info("Clicked on element: {}", element);
			ChainTestListener.log("Clicked on element:" + element);
		} catch (Exception e) {
			LOGGER.error("Element is not clickable : {}", e.getMessage());
			ChainTestListener.log("Element is not clickable :" + e.getMessage());
			throw e;
		}
	}

	protected void type(WebElement element, String text) {
		try {
			WaitUtils.waitForElementVisible(element);
			element.clear();
			element.sendKeys(text);
			LOGGER.info("Typed {} into element {}", text, element);
			ChainTestListener.log("Type "+ text +" into element" + element);
		} catch (Exception e) {
			LOGGER.error("Failed to type text : {}", e.getMessage());
			ChainTestListener.log("Failed to type text :" +e.getMessage());
			throw e;
		}
	}

	protected boolean isDisplayed(WebElement element) {
		try {
			WaitUtils.waitForElementVisible(element);
			return element.isDisplayed();
		} catch (Exception e) {
			LOGGER.error("Element is not displayed : {}", e.getMessage());
			ChainTestListener.log("Element is not displayed :" +e.getMessage());
			return false;
		}
	}

	protected String getText(WebElement element) {
		try {
			WaitUtils.waitForElementVisible(element);
			return element.getText();
		} catch (Exception e) {
			LOGGER.error("Failed to get text from element : {}", e.getMessage());
			ChainTestListener.log("Failed to get text from element :" +e.getMessage());
			return "";
		}
	}

	protected void selectByVisibleText(WebElement element, String text) {
		try {
			WaitUtils.waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByVisibleText(text);
			LOGGER.info("Selected {} from dropdown", text);
			ChainTestListener.log("Selected " + text + " from dropdown");
		} catch (Exception e) {
			LOGGER.error("Failed to select option : {}", e.getMessage());
			ChainTestListener.log("Failed to select option :" +e.getMessage());
			throw e;
		}
	}

	protected void selectByValue(WebElement element, String value) {
		try {
			WaitUtils.waitForElementVisible(element);
			Select select = new Select(element);
			select.selectByValue(value);
			LOGGER.info("Selected {} from dropdown", value);
			ChainTestListener.log("Selected " + value+" from dropdown");
		} catch (Exception e) {
			LOGGER.error("Failed to select option : {}", e.getMessage());
			ChainTestListener.log("Failed to select option :" +e.getMessage());
			throw e;
		}
	}
}
