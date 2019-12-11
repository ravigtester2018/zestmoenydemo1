package com.zest.pageobjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TripAdvisorPage extends BaseClass {

	public TripAdvisorPage() {
		super();
	}

	private void ExternalWaitCSS(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(findElementByCSS(locator)));
	}

	String tsearchIconCSS = "span[class='brand-global-nav-action-search-Search__label--3PRUD']";
	String tMainSearchBox = "#mainSearch";
	String tFirstSearchResultCSS = "ul[class='resultContainer local']>li:nth-of-type(1) span[class='poi-name primaryText']";
	 String tRatingBubbleCSS = "#bubble_rating";
	String tWriteAReviewButtonCSS = "div[class='hotels-community-content-common-ContextualCTA__currentOption--3Wd5D'] a[href*='UserReview']";
	 String tReviewTitle = "#ReviewTitle";
	 String tReviewBox = "#ReviewText";
	 String tbannerCloseCSS = "sbx_banner .sbx_close";
	// String fsearchBox = "input[title='Search for products, brands and
	// more']";
	// String fsearchBox1 = "input[name='q']";
	// String fgetPriceCSS = "a[href*='apple-iphone-xr-yellow-64-gb']
	// div[class='_1vC4OE _2rQ-NK']";
	// String fclosePopupButtonCSS = "button[class='_2AkmmA _29YdH8']";

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

	public void SearchAndGetFirstResult(WebDriver driver, String placeName) {
		pageLoad(driver, findElementByCSS(tsearchIconCSS));
		if (isElementPresent(driver, findElementByCSS(tsearchIconCSS))) {
			findElementByCSS(tsearchIconCSS).click();
			System.out.println("Search Icon Clicked...");
			findElementByCSS(tMainSearchBox).sendKeys(placeName);
			System.out.println("Place name entered in Main Search Box...");
			findElementByCSS(tMainSearchBox).click();
			System.out.println(" Search box clicked...");
			ExternalWaitCSS(tFirstSearchResultCSS);
			findElementByCSS(tFirstSearchResultCSS).click();
			System.out.println("First Element clicked...");
		}
	}

	public void WriteReview(WebDriver driver, String reviewTitle,String reviewText) throws AWTException {
		String parent = driver.getWindowHandle();
		if (isElementPresent(driver, findElementByCSS(tbannerCloseCSS))) {
			findElementByCSS(tbannerCloseCSS).click();
			System.out.println("Banner Closed.");
		}
		findElementByCSS(tWriteAReviewButtonCSS).click();
		System.out.println("Review button clicked...");
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if(!handle.equals(parent)){
				driver.switchTo().window(handle);
			}
		}
		System.out.println("Switch to new Tab");
		Actions action = new Actions(driver);
		
		Point point = findElementByCSS(tRatingBubbleCSS).getLocation();
		int x = point.getX();
		int y = point.getY();
		action.moveToElement(findElementByCSS(tRatingBubbleCSS),x,y).build().perform();
		
		Robot robot = new Robot();
		robot.mouseMove(x, y);
		robot.mouseMove(x, y+10);
		
		findElementByCSS(tReviewTitle).sendKeys(reviewTitle);
		findElementByCSS(tReviewBox).sendKeys(reviewText);
	}

}
