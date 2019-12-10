package com.zest.tests;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.zest.pageobjects.AmazonFlipkartPage;
import com.zest.pageobjects.BaseClass;
import com.zest.utilities.ReadPropertyFile;
import com.zest.utilities.TextFormatting;

public class AmazonFlipkartTest extends BaseClass {

	Logger log = Logger.getLogger(AmazonFlipkartTest.class.getName());
	ReadPropertyFile read;
	String aprice, fprice;
	TextFormatting format = new TextFormatting();

	public AmazonFlipkartTest() {
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

	@Test(priority = 1)
	public void AmazonSearchTest() throws IOException {
		AmazonFlipkartPage aloginPage = new AmazonFlipkartPage();
		getUrl(driver, read.returnPropertyValue("amzon_url"));
		// Search Amazon site and get price
		log.info("AmazonFlipkartPage Object Created");
		aprice = aloginPage.AmazonSearchItemAndGetPrice(driver, "iphone xr 64gb - yellow", 0);
		log.info("Item searched in Amazon and got the price." + aprice);
	}

	@Test(priority = 2)
	public void FlipkartSearchTest() throws IOException {
		getUrl(driver, read.returnPropertyValue("flipkart_url"));
		AmazonFlipkartPage floginPage = new AmazonFlipkartPage();
		// Search FlipKart site and get price
		String flipkart_url = read.returnPropertyValue("flipkart_url");
		log.info(flipkart_url + " - Page Loaded");
		fprice = floginPage.FlipKartSearchItemAndGetPrice(driver, "iphone xr 64gb - yellow", 0);
		log.info("Item searched in FlipKart and got the price.");
		// Compare Amazon Price Vs FlipKart Price
		aprice = format.removeSpecialCharacterFromStringl(aprice);
		System.out.println("Amzon Price : " + aprice);
		System.out.println("FlipKart Price : " + fprice);
		fprice = format.removeSpecialCharacterFromStringl(fprice);
		CompareAmazonFlipKartPrice(aprice, fprice);
	}

	public void CompareAmazonFlipKartPrice(String aprice1, String fprice1) {
		log.info("AmazonFlipkartPage Object Created");
		int apricenew = format.removeSpecialCharacterAndReturnIntVal(aprice);
		System.out.println("Final Amzon Price :" + apricenew);
		int fpricenew = format.removeSpecialCharacterAndReturnIntVal(fprice);
		System.out.println("Final FlipKart Price :" + fpricenew);
		System.out.println("Best Price is :" + format.ComparePrice(apricenew, fpricenew));
	}

	@AfterMethod
	public void afterMethod() {
		driver.close();
	}

	@AfterTest
	public void afterTest() {
		// driver.quit();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
