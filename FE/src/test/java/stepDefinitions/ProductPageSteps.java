package stepDefinitions;

import com.amazon.pages.ProductPage;
import com.amazon.utilities.Common;
import com.amazon.utilities.Constants;
import com.amazon.utilities.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductPageSteps {
    ProductPage productPage = new ProductPage(Common.getDriver());
    private static Logger logger = LoggerFactory.getLogger(ProductPageSteps.class);
    private ScenarioContext scenarioContext;

    public ProductPageSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    //region And
    @And("^User gets price of the product on product page for item (\\d+)$")
    public void userGetsPriceOfTheProductOnProductPage(int itemNumber) {
        logger.info("Storing the price on Product page via Scenario Context");
        String itemPrice = productPage.getPriceOnProductPage();
        scenarioContext.setContext(Constants.PRODUCT_PAGE_ITEM_PREFIX + itemNumber, itemPrice);
    }

    @And("^User clicks on search button$")
    public void userClicksOnSearchButton() {
        logger.info("Clicking on Search Button");
        productPage.clickSearchButton();
    }

    @And("^User clicks on Add to Cart$")
    public void userClicksOnAddToCart() {
        logger.info("Clicking on Add to Cart Button");
        productPage.clickButtonAddToCart();
    }
    //endregion

    //region Then
    @Then("^User verifies text \"([^\"]*)\" on product page for cart$")
    public void userVerifiesTextOnProductPageForCart(String expectedText) {
        logger.info("Verifying text on Product Page for Cart");
        //Act
        String actualText = productPage.getCartTextFromProductPage();

        //Assert
        Assert.assertEquals(expectedText, actualText);
    }

    @Then("^User verifies text \"([^\"]*)\" on product page$")
    public void verifyTextOnProductPage(String expectedText) {
        logger.info("Verifying text on Product Page upon landing");
        //Act
        String actualText = productPage.getTextFromProductPage();

        //Assert
        Assert.assertEquals(expectedText, actualText);
    }
    //endregion
}