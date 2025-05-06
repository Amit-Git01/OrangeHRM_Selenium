package com.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigReader {

	private static final Properties properties = new Properties();
	private static ConfigReader instance;
	private static final Logger LOGGER = LogManager.getLogger(ConfigReader.class);

	private ConfigReader() {
		final String path = "src/main/resources/config.properties";
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			properties.load(fis);
			fis.close();
//			System.out.println("File is loaded succesfully");
			LOGGER.info("File is loaded succesfully");

		} catch (FileNotFoundException e) {
//			System.out.println("File is not present in this path" + path);
			LOGGER.error("File is not present in this path : {}", path);
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ConfigReader getInstance() {

		if (instance == null) {
			instance = new ConfigReader();
		}
		return instance;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public int getIntProperty(String key) {
		String value = getProperty(key);
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
//			System.out.println("Error parsing integer property:");
			LOGGER.error("Error parsing integer property: {}", key);
			return 0;
		}
	}

	public boolean getBooleanProperty(String key) {
		String value = getProperty(key);
		return Boolean.parseBoolean(value);
	}
}
