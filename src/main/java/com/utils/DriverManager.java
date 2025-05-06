package com.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.base.PageDriver;
import com.reader.ConfigReader;

public class DriverManager {
	private static final ConfigReader configManager = ConfigReader.getInstance();
	private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);

	private DriverManager() {

	}

	public static void initializeDriver() {
	        String browser = configManager.getProperty("base.browser").toLowerCase();
	        boolean headless = configManager.getBooleanProperty("base.headless");
	        
	        WebDriver driver;

	        driver = switch (browser) {
	        case "chrome" -> createChromeDriver(headless);
	        case "edge" -> createEdgeDriver(headless);
	        default -> {
	        LOGGER.warn("Invalid browser '{}'. Defaulting to Chrome.", browser);
	        yield createChromeDriver(headless);
	        }
	    };
	        
	        

	        PageDriver.INSTANCE.setWebdriver(driver);
	        LOGGER.info("{} browser initialized", browser);
	        driver.manage().window().maximize();
	        String url = configManager.getProperty("base.url");
	        driver.get(url);
	        LOGGER.info("browser navigate to url: {}", url);
	        
	        WaitUtils.waitForPageLoad();
	    }

	private static WebDriver createChromeDriver(boolean headless) {

		ChromeOptions options = new ChromeOptions();
		if (headless) {
			options.addArguments("--headless");
		}
		options.addArguments("--disable-notifiactions");
		return new ChromeDriver(options);
	}

	private static WebDriver createEdgeDriver(boolean headless) {
		EdgeOptions options = new EdgeOptions();
		if (headless) {
			options.addArguments("headless");
		}
		return new EdgeDriver(options);
	}
}
