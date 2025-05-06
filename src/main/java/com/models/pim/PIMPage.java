package com.models.pim;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.models.BaseModel;
import com.models.DashboardPage;
import com.utils.WaitUtils;

public class PIMPage extends BaseModel {

	private static final Logger LOGGER = LogManager.getLogger(PIMPage.class);

	public PIMPage() {
		super();
	}

	@FindBy(xpath = "//h6[text()='PIM']")
	private WebElement pim_heading;

	@FindBy(css = ".oxd-topbar-body-nav-tab")
	private List<WebElement> pim_menu;

	public void verify_pim_page() {
		if (isDisplayed(pim_heading)) {
			String heading = getText(pim_heading).strip();
			Assert.assertEquals(heading, "PIM", "Assertion failed : " + getText(pim_heading));
			LOGGER.info("PIM heading is displayed: {}", heading);
		}
	}

	public void verify_title(String title) {

		try {
			String heading = getText(pim_heading).strip();
			Assert.assertEquals(heading, title, "Assertion failed : " + getText(pim_heading));
			LOGGER.info("PIM heading is displayed: {}", heading);
		} catch(Exception e) {
			ChainTestListener.log(e.getMessage());
		}
		
	}

	public AddEmployee clickOnAddEmployee() {
		clickOnMenu("add employee");
		LOGGER.info("Clicked on Add Employee");
		return new AddEmployee();

	}

	private void clickOnMenu(String menu) {

		WaitUtils.element_wait().until(ExpectedConditions.visibilityOfAllElements(pim_menu));

		for (WebElement element : pim_menu) {
			if (getText(element).strip().equalsIgnoreCase(menu)) {
//				System.out.println("Click on menu : " + getText(element));
				click(element);
				break;
			}
		}
	}
}
