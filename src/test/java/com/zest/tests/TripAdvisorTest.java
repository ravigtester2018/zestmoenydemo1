package com.zest.tests;

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zest.pageobjects.BaseClass;
import com.zest.pageobjects.TripAdvisorPage;
import com.zest.utilities.ReadPropertyFile;
import com.zest.utilities.TextFormatting;

public class TripAdvisorTest extends BaseClass {

	Logger log = Logger.getLogger(TripAdvisorTest.class.getName());
	ReadPropertyFile read;
	TextFormatting format = new TextFormatting();

	public TripAdvisorTest() {
		super();
	}

	@BeforeTest
	public void before() throws IOException {
	}

	@BeforeMethod
	public void beforeMethod() throws IOException {
		read = new ReadPropertyFile();
		driver = BaseClass(read);

	}

	@Test
	public void writeTripAdvisorReview() throws IOException, AWTException {
		TripAdvisorPage tAdvisorPage = new TripAdvisorPage();
		getUrl(driver, read.returnPropertyValue("tripadvisor_url"));
		// Search tripadvisor site
		log.info("Trip Advisor Page Object Created");
		// Choose first result from search result
		tAdvisorPage.SearchAndGetFirstResult(driver, "Club Mahindra");
		// Write review and submit
		tAdvisorPage.WriteReview(driver, "Test Review", "Some review added");
		System.out.println("Review Submitted successfully");
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}
