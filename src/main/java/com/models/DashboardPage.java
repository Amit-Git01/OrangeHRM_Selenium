package com.models;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.models.pim.PIMPage;
import com.utils.WaitUtils;

public class DashboardPage extends BaseModel {
	
	private static final Logger LOGGER = LogManager.getLogger(DashboardPage.class);

	public DashboardPage() {
		super();
	}

	@FindBy(xpath = "//h6[text()='Dashboard']")
	private WebElement dashboard_header;

	@FindBy(css = ".oxd-userdropdown-tab")
	private WebElement userDropdown;

	@FindBy(xpath = "//ul[@role='menu']/li/a")
	private List<WebElement> userMenuItems;

	@FindBy(css = ".oxd-main-menu-item")
	private List<WebElement> mainMenuItems;

	public void verify_dashboardPage_isDisplayed() {
		if (isDisplayed(dashboard_header)) {
			String heading = getText(dashboard_header).strip();
			Assert.assertEquals(heading, "Dashboard",
					"Assertion failed : " + getText(dashboard_header));
			LOGGER.info("Dashboard heading is displayed: {}", heading);
			
		}
		
	}

	public DashboardPage clickUserDropdown() {
		click(userDropdown);
		LOGGER.info("Clicked on user dropdown");
		return this;
	}

	public LoginPage logout() {

		clickUserDropdown();

		for (WebElement element : userMenuItems) {

			if (getText(element).strip().equalsIgnoreCase("Logout")) {
				click(element);
				LOGGER.info("Clicked on logout");
				break;
			}
		}
		return new LoginPage();
	}

	public PIMPage clickOnPIM() {
		clickOnMainMenu("pim");
		LOGGER.info("Clicked on PIM");
		return new PIMPage();

	}
	
	public void clickOnMenu(String menu) {
		clickOnMainMenu(menu);
		ChainTestListener.log("Clicked on menu : " + menu);
	}

	private void clickOnMainMenu(String menu) {
		
		WaitUtils.element_wait().until(ExpectedConditions.visibilityOfAllElements(mainMenuItems));

		for (WebElement element : mainMenuItems) {

//			System.out.println(getText(element));
			if (getText(element).strip().equalsIgnoreCase(menu)) {
				click(element);
				break;
			}
		}
	}

}
