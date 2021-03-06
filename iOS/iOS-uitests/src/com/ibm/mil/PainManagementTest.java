package com.ibm.mil;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.uiautomation.ios.client.uiamodels.impl.RemoteIOSDriver;

public class PainManagementTest {
	private static RemoteWebDriver driver;
	private static IosUtilities utils = new IosUtilities();
	private static SignInUtilities signInUtils = new SignInUtilities();

	@Before
	public void setup() throws Exception {
		driver = (RemoteWebDriver) utils.iosTestSetupWebDriver(driver);
	}

	@Test
	public void PainManagementScreenTest() throws Exception {
		// call the sign in and permission utility
		signInUtils.SignInAndPermissionUtilities(driver);

		// Wait for next page to load, aka wait for the ouch icon to appear
		utils.waitForElement(driver, "ouch icon");

		// Store the uiimages
		List<WebElement> uiImages = driver.findElements(By
				.className("UIAImage"));
		Assert.assertEquals(8, uiImages.size());

		// Select the menu button to reveal the menu
		WebElement menuButton = utils.waitForElement(driver, "menu icon");
		menuButton.click();

		// Wait for menu to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// check that the correct amount of StaticTexts are on the screen
		List<WebElement> staticTexts = driver.findElements(By
				.className("UIAStaticText"));
		Assert.assertEquals(45, staticTexts.size());

		// Save table cells in a list
		List<WebElement> tableCells = driver.findElements(By
				.className("UIATableCell"));
		Assert.assertEquals(6, tableCells.size());

		// click on the painManagementCell
		WebElement painManagementCell = utils.waitForElement(driver,
				"Pain Management");
		painManagementCell.click();

		// Wait for next screen to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		/*
		 * Pain Point Screen
		 */

		// verify the right number of text fields appeared
		List<WebElement> uiTextFields = driver.findElements(By
				.className("UIAStaticText"));
		Assert.assertEquals(5, uiTextFields.size());

		// verify the right number of buttons appeared
		List<WebElement> uiButtons = driver.findElements(By
				.className("UIAButton"));
		Assert.assertEquals(7, uiButtons.size());

		// check for the "next" button to appear, which is on the pain point
		// screen
		WebElement next_button = utils.waitForElement(driver, "Next");
		Assert.assertNotNull(
				"Next button exists, so pain point screen was successfully loaded",
				next_button);

		// check that the front of the body is selected
		WebElement front_button = utils.waitForElement(driver, "FRONT");
		Assert.assertNotNull("front button is available", front_button);

		// click on the back body button to display the back of the body
		WebElement back_button = utils.waitForElement(driver, "BACK");
		Assert.assertNotNull("back button is available", back_button);
		back_button.click();

		// Wait for back body to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// click on the front body part again
		front_button.click();

		next_button.click();

		WebElement popup = utils.waitForElement(driver,
				"Please select a body part before continuing");
		Assert.assertNotNull(popup);
		List<WebElement> uiScrollView = utils.waitForListElements(driver,
				"UIAScrollView", 10);
		uiScrollView.get(0).click();
		uiScrollView.get(0).click();

		// Wait for front body to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		// click on the next button
		next_button.click();

		/*
		 * Pain Report Screen
		 */
		// check for the submit button, which currently is only on the pain
		// management screen

		// check to see that the pain meter is there
		List<WebElement> CollectionCells = utils.waitForListElements(driver,
				"UIACollectionCell", 10);

		// check the 3 cell
		WebElement three_cell = CollectionCells.get(3);
		three_cell.click();
		Assert.assertNotNull("Collection cell of 3 is available", three_cell);

		// check the 4 cell
		WebElement four_cell = CollectionCells.get(4);
		four_cell.click();
		Assert.assertNotNull("Collection cell of 4 is available", four_cell);

		// check the 6 cell
		WebElement six_cell = CollectionCells.get(5);
		six_cell.click();
		Assert.assertNotNull("Collection cell of 6 is available", six_cell);

		// check the 7 cell
		WebElement seven_cell = CollectionCells.get(6);
		seven_cell.click();
		Assert.assertNotNull("Collection cell of 7 is available", seven_cell);

		// click on the description and write a description
		List<WebElement> textView = driver.findElements(By
				.className("UIATextView"));
		WebElement descriptionTextView = textView.get(0);
		descriptionTextView.click();
		descriptionTextView.sendKeys("I really hurt =(");

		// click on the submit button
		WebElement submit_button = utils.waitForElement(driver, "Submit");
		submit_button.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		WebElement noButton = utils.waitForElement(driver, "No");
		noButton.click();

		textView = driver.findElements(By.className("UIATextView"));
		descriptionTextView = textView.get(0);
		descriptionTextView.click();
		descriptionTextView.sendKeys(" Like Alot");

		submit_button = utils.waitForElement(driver, "Submit");
		submit_button.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		WebElement yesButton = utils.waitForElement(driver, "Yes");
		yesButton.click();

	}

	@Test
	public void ouchButtonTest() throws Exception {
		signInUtils.SignInAndPermissionUtilities(driver);
		// verify that the dashboard appeared again
		// Wait for next page to load, aka wait for the ouch icon to appear
		WebElement ouch_icon = utils.waitForElement(driver, "ouch icon");
		Assert.assertNotNull("Ouch button is present", ouch_icon);

		// click on the ouch_icon, which is the alternate way to get to the pain
		// management screen
		ouch_icon.click();

		// Wait for a bit so if we're looking at the test we can see what's
		// going on
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		// Wait for next screen to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		/*
		 * Pain Point Screen
		 */

		// verify the right number of text fields appeared
		List<WebElement> uiTextFields = driver.findElements(By
				.className("UIAStaticText"));
		Assert.assertEquals(5, uiTextFields.size());

		// verify the right number of buttons appeared
		List<WebElement> uiButtons = driver.findElements(By
				.className("UIAButton"));
		Assert.assertEquals(7, uiButtons.size());

		// check for the "next" button to appear, which is on the pain point
		// screen
		WebElement next_button = utils.waitForElement(driver, "Next");
		Assert.assertNotNull(
				"Next button exists, so pain point screen was successfully loaded",
				next_button);

		// check that the front of the body is selected
		WebElement front_button = utils.waitForElement(driver, "FRONT");
		Assert.assertNotNull("front button is available", front_button);

		// click on the back body button to display the back of the body
		WebElement back_button = utils.waitForElement(driver, "BACK");
		Assert.assertNotNull("back button is available", back_button);
		back_button.click();

		// Wait for back body to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}

		// click on the front body part again
		front_button.click();

		next_button.click();

		WebElement popup = utils.waitForElement(driver,
				"Please select a body part before continuing");
		Assert.assertNotNull(popup);
		List<WebElement> uiScrollView = utils.waitForListElements(driver,
				"UIAScrollView", 10);
		uiScrollView.get(0).click();
		uiScrollView.get(0).click();

		// Wait for front body to load
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		// click on the next button
		next_button.click();

		/*
		 * Pain Report Screen
		 */
		// check for the submit button, which currently is only on the pain
		// management screen

		// check to see that the pain meter is there
		List<WebElement> CollectionCells = utils.waitForListElements(driver,
				"UIACollectionCell", 10);

		// check the 3 cell
		WebElement three_cell = CollectionCells.get(3);
		three_cell.click();
		Assert.assertNotNull("Collection cell of 3 is available", three_cell);

		// check the 4 cell
		WebElement four_cell = CollectionCells.get(4);
		four_cell.click();
		Assert.assertNotNull("Collection cell of 4 is available", four_cell);

		// check the 6 cell
		WebElement six_cell = CollectionCells.get(5);
		six_cell.click();
		Assert.assertNotNull("Collection cell of 6 is available", six_cell);

		// check the 7 cell
		WebElement seven_cell = CollectionCells.get(6);
		seven_cell.click();
		Assert.assertNotNull("Collection cell of 7 is available", seven_cell);

		// click on the description and write a description
		List<WebElement> textView = driver.findElements(By
				.className("UIATextView"));
		WebElement descriptionTextView = textView.get(0);
		descriptionTextView.click();
		descriptionTextView.sendKeys("I really hurt =(");

		// click on the submit button
		WebElement submit_button = utils.waitForElement(driver, "Submit");
		submit_button.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		WebElement noButton = utils.waitForElement(driver, "No");
		noButton.click();

		textView = driver.findElements(By.className("UIATextView"));
		descriptionTextView = textView.get(0);
		descriptionTextView.click();
		descriptionTextView.sendKeys(" Like Alot");

		submit_button = utils.waitForElement(driver, "Submit");
		submit_button.click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		WebElement yesButton = utils.waitForElement(driver, "Yes");
		yesButton.click();

	}

	@After
	public void stop() {
		// Close the session
		utils.iosTestTeardownDriver(driver);
	}
}