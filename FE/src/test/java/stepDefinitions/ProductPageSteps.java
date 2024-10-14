package stepDefinitions;

import com.amazon.pages.ProductPage;
import com.amazon.pages.SearchResultPage;
import com.amazon.utilities.Common;
import com.amazon.utilities.Constants;
import com.amazon.utilities.ScenarioContext;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class ProductPageSteps {
    ProductPage productPage = new ProductPage(Common.getDriver());

    private ScenarioContext scenarioContext;

    public ProductPageSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    //region When
    //endregion

    //region And
    @And("^User gets price of the product on product page for item (\\d+)$")
    public void userGetsPriceOfTheProductOnProductPage(int itemNumber) {
        String itemPrice = productPage.getPriceOnProductPage();
        scenarioContext.setContext(Constants.PRODUCT_PAGE_ITEM_PREFIX +itemNumber,itemPrice);
    }

    @And("^User clicks on search button$")
    public void userClicksOnSearchButton() {
        productPage.clickSearchButton();
    }

    @And("^User clicks on Add to Cart$")
    public void userClicksOnAddToCart() {
        productPage.clickButtonAddToCart();
    }
    //endregion

    //region Then
    @Then("^User verifies text \"([^\"]*)\" on product page for cart$")
    public void userVerifiesTextOnProductPageForCart(String expectedText){
        //Act
        String actualText = productPage.getCartTextFromProductPage();

        //Assert
        Assert.assertEquals(expectedText, actualText);
    }

    @Then("^User verifies text \"([^\"]*)\" on product page$")
    public void verifyTextOnProductPage(String expectedText) {
        //Act
        String actualText = productPage.getTextFromProductPage();

        //Assert
        Assert.assertEquals(expectedText, actualText);
    }
    //endregion
}
