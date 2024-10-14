package com.amazon.pages;

import com.amazon.utilities.Common;
import com.amazon.utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class TotalSummaryPage extends Common {
    public TotalSummaryPage(WebDriver driver) {
        this.driver = driver;
    }

    private By get_PriceOnTotalSummaryPage = By.xpath("//span[@class='ewc-subtotal-amount']/h2");

    public String getPriceOnProductPage(){
        driver.manage().timeouts().implicitlyWait(Constants.MEDIUM_TIMEOUT, TimeUnit.SECONDS);
        String priceWhole_OnSearchResultPage;

        waitForPresenceWebElement(driver, get_PriceOnTotalSummaryPage, Constants.MEDIUM_TIMEOUT);
        priceWhole_OnSearchResultPage = getElement(driver, get_PriceOnTotalSummaryPage).getText();
        String removeSubString = priceWhole_OnSearchResultPage.replace("$", "");
        System.out.println("Price on TotalSummary Page of item:  " +removeSubString);

        return removeSubString;
    }
}
