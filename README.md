OrangeHRM Selenium Automation Project
Overview
This repository contains a Selenium-based automation framework for testing the OrangeHRM web application. It is built using Java, enabling robust and efficient testing of OrangeHRM's features.

Features
Automated testing scripts for OrangeHRM functionalities.
Written in Java with Selenium for browser automation.
Modular and reusable test cases.
Easy integration with CI/CD pipelines.
Prerequisites
Before you begin, ensure you have met the following requirements:

Java Development Kit (JDK): Version 8 or later.
Maven: For dependency management.
Selenium WebDriver: Installed and configured.
Web Browser: Chrome, Firefox, or any supported browser.
IDE: IntelliJ IDEA, Eclipse, or any Java-supported IDE.
Installation
Clone the repository:

bash
git clone https://github.com/Amit-Git01/OrangeHRM_Selenium.git
Navigate to the project directory:

bash
cd OrangeHRM_Selenium
Install dependencies:

bash
mvn clean install
Usage
Open the project in your preferred IDE.

Configure the WebDriver for your target browser (e.g., ChromeDriver).

Update the configuration file (if applicable) with the required details (e.g., URL, login credentials).

Run the test suite with Maven:

bash
mvn test
Project Structure
src/main/java: Contains the main Java code.
src/test/java: Contains the Selenium test scripts.
resources: Configuration files and test data.
