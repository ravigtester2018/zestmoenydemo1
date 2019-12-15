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

	String tsearchIconCSS = "span[class='ui_icon search brand-global-nav-action-search-Search__icon--2DVjd']";
	String tMainSearchBox = "#mainSearch";
	String tFirstSearchResultCSS = "ul[class='resultContainer local']>li:nth-of-type(1) span[class='poi-name primaryText']";
	String tRatingBubbleCSS = "#bubble_rating";
	String tWriteAReviewButtonCSS = "div[class='hotels-community-content-common-ContextualCTA__currentOption--3Wd5D'] a[href*='UserReview']";
	String tReviewTitle = "#ReviewTitle";
	String tReviewBox = "#ReviewText";
	String tbannerCloseCSS = "sbx_banner .sbx_close";
	String tsubmitReviewCheckbox = "input[name='noFraud']";
	String tsubmitReviewButton = "div[id='SUBMIT']";

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

	public void WriteReview(WebDriver driver, String reviewTitle, String reviewText) throws AWTException {
		String parent = driver.getWindowHandle();
		try {
			if (isElementPresent(driver, findElementByCSS(tbannerCloseCSS))) {
				findElementByCSS(tbannerCloseCSS).click();
				System.out.println("Banner Closed.");
			}
		} catch (Exception e) {
			System.out.println("Banner is not Displayed. No Issues.");
		}
		findElementByCSS(tWriteAReviewButtonCSS).click();
		System.out.println("Review button clicked...");
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(parent)) {
				driver.switchTo().window(handle);
			}
		}
		System.out.println("Switch to new Tab");
		Actions action = new Actions(driver);
		action.moveToElement(findElementByCSS(tRatingBubbleCSS), 17, 0).perform();
		action.moveToElement(findElementByCSS(tRatingBubbleCSS), 49, 0).perform();
		action.moveToElement(findElementByCSS(tRatingBubbleCSS), 49 + 17 + 17, 0).perform();
		action.moveToElement(findElementByCSS(tRatingBubbleCSS), 49 + 17 + 17 + 17 + 17, 0).perform();
		action.moveToElement(findElementByCSS(tRatingBubbleCSS), 49 + 17 + 17 + 17 + 17 + 17 + 17, 0).click().build()
				.perform();
		findElementByCSS(tReviewTitle).sendKeys(reviewTitle);
		findElementByCSS(tReviewBox).sendKeys(reviewText);
		try {
			findElementByCSS(tsubmitReviewCheckbox).click();
			if (findElementByCSS(tsubmitReviewCheckbox).isSelected()) {
				System.out.println("Yes. Reviewcheckbix is selected.");
			} else {
				System.out.println("No. Reviewcheckbix is not selected. Selecting one more time.");
				findElementByCSS(tsubmitReviewCheckbox).click();
			}
		} catch (Exception e) {
			System.out.println("Exception in CheckBox selection.");
		}
		findElementByCSS(tsubmitReviewButton).click();
	}

}
