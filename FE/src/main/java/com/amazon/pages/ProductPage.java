package com.amazon.pages;

import com.amazon.utilities.Common;
import com.amazon.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ProductPage extends Common {


    private By get_TextOn_ProductPage = By.xpath("//a[normalize-space()=\"Back to results\"]");
    private By get_CartTextOn_ProductPage = By.xpath("//h1[normalize-space()='Added to Cart']");
    private By get_Whole_PriceOfItemByIndex = By.xpath("(//span[@class='a-price-whole'])[1]");
    private By get_Fraction_PriceOfItemByIndex = By.xpath("(//span[@class='a-price-fraction'])[1]");
    private By searchBoxButton = By.xpath("//input[@id='nav-search-submit-button']");
    private By addToCartButton = By.xpath("//input[@id='add-to-cart-button']");

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPage.class);
    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextFromProductPage() {
        waitForVisibleWebElement(driver, get_TextOn_ProductPage, Constants.MEDIUM_TIMEOUT);
        return getElement(driver, get_TextOn_ProductPage).getText();
    }

    public String getCartTextFromProductPage() {
        waitForVisibleWebElement(driver, get_CartTextOn_ProductPage, Constants.MEDIUM_TIMEOUT);
        return getElement(driver, get_CartTextOn_ProductPage).getText();
    }

    public String getPriceOnProductPage() {
        driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        String priceWhole_OnSearchResultPage;
        String priceFraction_OnSearchResultPage;

        waitForPresenceWebElement(driver, get_Whole_PriceOfItemByIndex, Constants.MEDIUM_TIMEOUT);
        priceWhole_OnSearchResultPage = getElement(driver, get_Whole_PriceOfItemByIndex).getText();
        priceFraction_OnSearchResultPage = getElement(driver, get_Fraction_PriceOfItemByIndex).getText();

        return priceWhole_OnSearchResultPage + "." + priceFraction_OnSearchResultPage;
    }

    public void clickSearchButton() {
        getElement(driver, searchBoxButton).click();
    }

    public void clickButtonAddToCart() {
        getElement(driver, addToCartButton).click();
    }

}