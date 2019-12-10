package com.zest.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.zest.utilities.ReadPropertyFile;

public class BaseClass {
	protected static WebDriver driver;
	private FluentWait<WebElement> wait;

	public BaseClass() {
		driver = this.driver;
	}

	public WebDriver BaseClass(ReadPropertyFile read) throws IOException {
		String val = read.returnPropertyValue("browser");
		if (val.toLowerCase().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "D:/WS3/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		if (val.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:/WS3/chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	public void getUrl(WebDriver driver, String url) {
		driver.get(url);
		System.out.println(driver.getCurrentUrl() + " - Page Loaded.");
	}

	protected Boolean isElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		return element.isDisplayed();
	}

}
