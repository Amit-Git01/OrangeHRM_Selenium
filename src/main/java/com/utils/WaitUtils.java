package com.utils;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.base.PageDriver;
import com.reader.ConfigReader;

public class WaitUtils {

	private static final ConfigReader configManager = ConfigReader.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(WaitUtils.class);
	private static final int TIMEOUT = configManager.getIntProperty("wait.timeout");
	private static final int POLLING = configManager.getIntProperty("wait.polling");

	private WaitUtils() {

	}

	public static WebDriverWait element_wait(int seconds) {
		return new WebDriverWait(PageDriver.getCurrentDriver(), Duration.ofSeconds(seconds));
	}

	public static WebDriverWait element_wait() {
		return element_wait(TIMEOUT);
	}

	public static WebElement waitForElementVisible(WebElement element, int seconds) {
		try {
			return element_wait(seconds).until(ExpectedConditions.visibilityOf(element));
		} catch (TimeoutException e) {
			LOGGER.error("Element is not visible after {} seconds", seconds);
			throw e;
		}
	}

	public static WebElement waitForElementVisible(WebElement element) {
		return waitForElementVisible(element, TIMEOUT);
	}

	public static WebElement waitForElementClickable(WebElement element, int seconds) {
		try {
			return element_wait(seconds).until(ExpectedConditions.elementToBeClickable(element));
		} catch (TimeoutException e) {
			LOGGER.error("Element is not clickable after {} seconds", seconds);
			throw e;
		}
	}

	public static WebElement waitForElementClickable(WebElement element) {
		return waitForElementClickable(element, TIMEOUT);
	}

	public static WebElement waitForElementPresent(WebElement element, int seconds) {
		ExpectedCondition<WebElement> pageCondition = driver -> {
			return element.isDisplayed() ? element : null;
		};

		try {
			return element_wait().until(pageCondition);
		} catch (TimeoutException e) {
			LOGGER.error("Element is not present after {} seconds", seconds);
			throw e;
		}

	}

	public static WebElement waitForElementPresent(WebElement element) {
		return waitForElementPresent(element, TIMEOUT);
	}

	public static void waitForPageLoad(int seconds) {
		ExpectedCondition<Boolean> pageLoadCondition = driver -> {
			return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
		};

		try {
			element_wait(seconds).until(pageLoadCondition);
		} catch (TimeoutException e) {
			LOGGER.error("Page is not loaded after {} seconds", seconds);
			throw e;
		}
	}

	public static void waitForPageLoad() {
		waitForPageLoad(TIMEOUT);
	}

	public static Wait<WebDriver> getFluentWait(int timeoutSeconds) {
		return new FluentWait<>(PageDriver.getCurrentDriver()).withTimeout(Duration.ofSeconds(timeoutSeconds))
				.pollingEvery(Duration.ofMillis(POLLING)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
	}

	public static Wait<WebDriver> getFluentWait() {
		return getFluentWait(TIMEOUT);
	}
}
