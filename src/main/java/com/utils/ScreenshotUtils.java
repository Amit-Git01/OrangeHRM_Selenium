package com.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.base.PageDriver;

public class ScreenshotUtils {

	private static final Logger LOGGER = LogManager.getLogger(ScreenshotUtils.class);

	private ScreenshotUtils() {

	}

	public static void takesScreenshot(String testname) {
		WebDriver driver = PageDriver.getCurrentDriver();

		byte[] srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

		ChainTestListener.embed(srcFile, "image/png");

		saveScreenshotLocally(srcFile, testname);
		LOGGER.info("Screenshot taken.");

	}

	public static void saveScreenshotLocally(byte[] screenshotBytes, String testName) {
		String timestamp = LocalDateTime.now().toString().replace(":", "-");
		String filename = "screenshots/" + testName + "_" + timestamp + ".png";

		try {
			Path path = Paths.get(filename);
			Files.createDirectories(path.getParent()); // Ensure folder exists
			Files.write(path, screenshotBytes);
			System.out.println("Screenshot saved: " + filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
