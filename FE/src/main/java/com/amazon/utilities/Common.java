package com.amazon.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.config.ConfigPropFileData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Common {

    private static Logger logger = LoggerFactory.getLogger(Common.class);

    public static WebDriver driver;

    public Common() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriver initializeDriver() {
        String browser = ConfigPropFileData.getInstance().getBrowser();
        if (browser.equals(Constants.CHROME)) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
         else if (browser.equals(Constants.FIREFOX)) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        }
        driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        return driver;
    }

    public static void waitForVisibleWebElement(WebDriver driver, By element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void waitForElementToBeClickable(WebDriver driver, By locator, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitForPresenceWebElement(WebDriver driver, By element, long timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static WebElement getElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }

    public List<String> getAllElementsText(WebDriver driver, By locator) {
        List<WebElement> buttonElements = driver.findElements(locator);

        // Extract non-empty text from each button element
        List<String> buttonNames = new ArrayList<>();
        for (WebElement button : buttonElements) {
            String text = button.getText().trim(); // Trim whitespace
            if (!text.isEmpty()) { // Check if the text is not empty
                buttonNames.add(text);
            }
        }
        return buttonNames;
    }

    public static void scrollToWithJS(WebDriver driver, By locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", driver.findElement(locator));

        int extraScrollPixels = 100; // Adding more as it is required in my case
        javascriptExecutor.executeScript("window.scrollBy(0, arguments[0]);", extraScrollPixels);
    }

    public static void scrollToWithJS1(WebDriver driver, By locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator));
    }

    public void scrollToElementWithActions(WebDriver driver, By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public static void scrollToBottom(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollTo(0, arguments[0].scrollHeight)", driver.findElement(locator));
    }

    public static boolean waitForSpecificUrl(WebDriver driver, String expectedUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
        return wait.until(ExpectedConditions.urlMatches(expectedUrl));
    }
}
