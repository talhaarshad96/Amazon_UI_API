package stepDefinitions;

import com.amazon.pages.SearchResultPage;
import com.amazon.utilities.Constants;
import com.amazon.utilities.ScenarioContext;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.amazon.utilities.Common;

public class SearchResultSteps {
    SearchResultPage searchResultPage = new SearchResultPage(Common.getDriver());

    private static Logger logger = LoggerFactory.getLogger(SearchResultSteps.class);

    private ScenarioContext scenarioContext;

    public SearchResultSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


    //region Given
    @Given("User navigates to amazon url")
    public void userNavigatesToAmazonUrl() {
        logger.info("Navigating to amazon.com");
    }
    //endregion

    //region And
    @And("^User perform captcha and close the location alert$")
    public void userPerformCaptchaAndCloseTheLocationAlert() throws InterruptedException {
        searchResultPage.closeAlert();
    }

    @And("^User select item (\\d+)$")
    public void userSelectItem(int itemNumber) throws InterruptedException {
        searchResultPage.selectTheItem(itemNumber);
    }
    //endregion

    //region When
    @When("^search for product \"([^\"]*)\"$")
    public void searchForProduct(String productName){
        searchResultPage.clickSearchBoxAndEnterItemNameAndClickSearch(productName);
    }
    //endregion

    //region Then
    @Then("^User verifies text \"([^\"]*)\" on search result page$")
    public void userVerifiesTextOnLandingPage(String expectedText) {
        //Act
        String actualText = searchResultPage.getTextOnLandingPage();

        //Assert
        Assert.assertEquals(expectedText,actualText);
    }

    @And("^User gets price of the product on search page for item (\\d+)$")
    public void userGetsPriceOfTheProductOnSearchPage(int itemNumber) throws InterruptedException {
        String itemPrice = searchResultPage.getPriceOnSearchPage(itemNumber);
        scenarioContext.setContext(Constants.SEARCH_PAGE_ITEM_PREFIX +itemNumber,itemPrice);
    }
    //endregion
}