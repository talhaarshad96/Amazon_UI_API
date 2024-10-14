package com.amazon.pages;

import com.amazon.utilities.Common;
import com.amazon.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SearchResultPage extends Common {
    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    private By searchBoxField = By.xpath("//input[@id='twotabsearchtextbox']");
    private By dismissAlertBox = By.xpath("//input[@data-action-params='{\"toasterType\":\"AIS_INGRESS\"}']");
    private By searchBoxButton = By.xpath("//input[@id='nav-search-submit-button']");
    private By get_TextOn_LandingPage = By.xpath("//a[normalize-space()=\"Today's Deals\"]");
    private String selectTheItemByIndex = "(//span[@class='a-price'])[%s]";
    private String get_Whole_PriceOfItemByIndex = "(//span[@class='a-price-whole'])[%s]";
    private String get_Fraction_PriceOfItemByIndex = "(//span[@class='a-price-fraction'])[%s]";

    public SearchResultPage clickSearchBoxAndEnterItemNameAndClickSearch(String itemName) {
        waitForVisibleWebElement(driver, searchBoxField, Constants.MEDIUM_TIMEOUT);
        waitForElementToBeClickable(driver, searchBoxField, Constants.MEDIUM_TIMEOUT);
        getElement(driver, searchBoxField).sendKeys(itemName);
        getElement(driver, searchBoxButton).click();
        return this;
    }

    public String getTextOnLandingPage() {
        waitForVisibleWebElement(driver, get_TextOn_LandingPage, Constants.MEDIUM_TIMEOUT);
        return getElement(driver, get_TextOn_LandingPage).getText();
    }

    public void selectTheItem(Integer itemNum) {
        driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        waitForPresenceWebElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum)), Constants.MEDIUM_TIMEOUT);
        waitForVisibleWebElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum)), Constants.MEDIUM_TIMEOUT);
        getElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum))).click();
    }

    public String getPriceOnSearchPage(Integer itemNum) {
        String priceWhole_OnSearchResultPage;
        String priceFraction_OnSearchResultPage;
        driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        waitForPresenceWebElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum)), Constants.MEDIUM_TIMEOUT);
        waitForVisibleWebElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum)), Constants.MEDIUM_TIMEOUT);

        scrollToElement(driver, By.xpath(String.format(selectTheItemByIndex, itemNum)));
        priceWhole_OnSearchResultPage = getElement(driver, By.xpath(String.format(get_Whole_PriceOfItemByIndex, itemNum))).getText();
        priceFraction_OnSearchResultPage = getElement(driver, By.xpath(String.format(get_Fraction_PriceOfItemByIndex, itemNum))).getText();

        return priceWhole_OnSearchResultPage + "." + priceFraction_OnSearchResultPage;
    }

    public void closeAlert() {
        driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS); // wait for captcha
        waitForVisibleWebElement(driver, dismissAlertBox, Constants.LONG_TIMEOUT);
        try {
            getElement(driver, dismissAlertBox).click();
        } catch (ElementClickInterceptedException e) {
            getElement(driver, dismissAlertBox).click();
        }
    }
}