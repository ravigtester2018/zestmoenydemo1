package com.zest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonFlipkartPage extends BaseClass {

	public AmazonFlipkartPage() {
		super();
	}

	private void ExternalWaitCSS(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(findElementByCSS(locator)));
	}

	String asearchIconCSS = "#nav-search-submit-text";
	String asearchBoxCSS = "#twotabsearchtextbox";
	String asearchGoButtonCSS = "input[value='Go']";
	String asearchResultCSS = "div[data-index] span.a-price";
	String agetPriceCSS = "a[href*='Apple-iPhone-XR-64GB-Yellow'] > span[class='a-price'] span[class='a-price-whole']";
	String asearchBarCSS = ".nav-searchbar";
	String fsearchButtonCSS = "button[type='submit']";
	String fsearchButtonXpath = "//button[@type='submit']";
	String fsearchBox = "input[title='Search for products, brands and more']";
	String fsearchBox1 = "input[name='q']";
	String fgetPriceCSS = "a[href*='apple-iphone-xr-yellow-64-gb'] div[class='_1vC4OE _2rQ-NK']";
	String fclosePopupButtonCSS = "button[class='_2AkmmA _29YdH8']";

	WebElement findElementByCSS(String locator) {
		return driver.findElement(By.cssSelector(locator));
	}

	List<WebElement> findListOfElementsByCSS(String locator) {
		return driver.findElements(By.cssSelector(locator));
	}

	WebElement findElementByXpath(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public Boolean pageLoad(WebDriver driver, WebElement element) {
		Boolean elementPresent = isElementPresent(driver, element);
		if (elementPresent) {
			System.out.println(driver.getCurrentUrl() + " - Page Loaded Successfully.");
		} else {
			System.out.println(driver.getCurrentUrl() + " - Page Not Loaded Properly.");
		}
		return elementPresent;
	}

	public String AmazonSearchItemAndGetPrice(WebDriver driver, String itemname, int itemno) {
		pageLoad(driver, findElementByCSS(asearchBarCSS));
		String price = "";
		if (isElementPresent(driver, findElementByCSS(asearchGoButtonCSS))) {
			findElementByCSS(asearchBoxCSS).sendKeys(itemname);
			System.out.println("Search Item Sent...");
			findElementByCSS(asearchGoButtonCSS).click();
			System.out.println("Search button clicked...");
			price = findElementByCSS(agetPriceCSS).getText();
			System.out.println("Amazon Price = " + price);
		}
		return price;
	}

	public String FlipKartSearchItemAndGetPrice(WebDriver driver, String itemname, int itemno) {
		if (isElementPresent(driver, findElementByCSS(fclosePopupButtonCSS))) {
			findElementByCSS(fclosePopupButtonCSS).click();
			System.out.println("Popup closed");
		}
		pageLoad(driver, findElementByCSS(fsearchBox));
		String price = "";
		if (isElementPresent(driver, findElementByCSS(fsearchBox1))) {
			findElementByCSS(fsearchBox).sendKeys(itemname);
			System.out.println("Search Item Sent...");
			findElementByCSS(fsearchButtonCSS).click();
			System.out.println("Search button clicked...");
			ExternalWaitCSS(fgetPriceCSS);
			System.out.println("Wait completed");
			price = findElementByCSS(fgetPriceCSS).getText();
			System.out.println("Collected Price.");
			System.out.println("FlipKart Price = " + price);
		}
		return price;
	}

	public String getPriceFromList(List<WebElement> list, int index) {
		String price = list.get(0).getText();
		return price;
	}
}
