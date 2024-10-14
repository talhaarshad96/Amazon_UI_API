package stepDefinitions;

import com.amazon.pages.TotalSummaryPage;
import com.amazon.utilities.Common;
import com.amazon.utilities.ScenarioContext;
import com.amazon.utilities.Constants;
import cucumber.api.java.en.Then;
import org.junit.Assert;

public class TotalSummarySteps {

    TotalSummaryPage totalSummaryPage = new TotalSummaryPage(Common.getDriver());

    private ScenarioContext scenarioContext;

    public TotalSummarySteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    //region And
    @Then("^User verifies the prices of products (\\d+) and (\\d+) on Search page, product page, Total Summary page$")
    public void userVerifiesThePricesOfProductsOnSearchPageProductPageTotalSummaryPage(String item1, String item2) {
        String priceSearchPage1 = (String) scenarioContext.getContext(Constants.SEARCH_PAGE_ITEM_PREFIX+item1);
        String priceSearchPage2 = (String) scenarioContext.getContext(Constants.SEARCH_PAGE_ITEM_PREFIX+item2);

        String priceProductPage1 = (String) scenarioContext.getContext(Constants.PRODUCT_PAGE_ITEM_PREFIX+item1);
        String priceProductPage2 = (String) scenarioContext.getContext(Constants.PRODUCT_PAGE_ITEM_PREFIX+item2);

        Double totalProductPage = Double.parseDouble(priceProductPage1) + Double.parseDouble(priceProductPage2);

        Double totalSearchPage = Double.parseDouble(priceSearchPage1) + Double.parseDouble(priceSearchPage2);
        Double totalSummaryPrice = Double.parseDouble(totalSummaryPage.getPriceOnProductPage());
        System.out.println("Total Search Price : "+totalSearchPage);
        System.out.println("Total Product Price : "+totalProductPage);
        System.out.println("Total Summary Price : "+totalSummaryPrice);

        //Assert
        Assert.assertEquals(totalSearchPage,totalProductPage);
        Assert.assertEquals(totalProductPage,totalSummaryPrice);
        Assert.assertEquals(totalSearchPage,totalSummaryPrice);
    }
    //endregion
}
