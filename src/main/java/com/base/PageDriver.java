package com.base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public enum PageDriver {

	INSTANCE;
	private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
	private static final Logger LOGGER = LogManager.getLogger(PageDriver.class);

	public WebDriver getWebdriver() {
		return webdriver.get();
	}

	public void setWebdriver(WebDriver driver) {
		if (getWebdriver() == null) {
			webdriver.set(driver);
		} else {
			LOGGER.error("Webdriver is already set and cannot be changed");
			throw new IllegalStateException("Webdriver is already set and cannot be changed");
		}
	}

	public static WebDriver getCurrentDriver() {
		return INSTANCE.getWebdriver();
	}

	public void quitDriver() {
		WebDriver driver = getWebdriver();
		if (driver != null) {
			driver.quit();
			webdriver.remove();
			LOGGER.info("Browser is closed.");
		}
	}
}
