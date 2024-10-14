package stepDefinitions;

import config.BEConfigPropFileData;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import cucumber.api.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;

public class herukuPostAndGetRequest {

    public Response response;
    private RequestSpecification request;
    BEConfigPropFileData configPropFileData = new BEConfigPropFileData();
    public static String bookingid;
    public static String postedFName;
    public static String postedLName;
    public static String totalPrice;
    public static String depositPaid;
    public static String bookingDatesCheckIn;
    public static String bookingDatesCheckOut;
    public static String additionalneeds;

    @Given("^Get API is provided$")
    public void getAPIIsProvided() {
        request = RestAssured.given();
    }

    @When("^User perform Get Operation$")
    public void user_perform_Get_Operation(){

        response = given().log().all().header("Content-type", "application/json")

                .when()
                .get(configPropFileData.getServer()+configPropFileData.getEndpoint()+"/"+bookingid)
                .then()
                .extract().response();

        System.out.println("Get response \n");
        response.prettyPrint();
    }

    @When("^User perform Post Operation$")
    public void user_perform_Post_Operation() {
        JSONObject jsonObject = new JSONObject();
        JSONObject bookingDates = new JSONObject();

        //inserting key value pair to jsonObject of our api body
        jsonObject.put("firstname", configPropFileData.getFirstName());
        jsonObject.put("lastname", configPropFileData.getLastName());
        jsonObject.put("totalprice", configPropFileData.getTotalPrice());
        jsonObject.put("depositpaid", configPropFileData.getDepositPaid());
        bookingDates.put("checkin", configPropFileData.getBookingDateCheckIn());
        bookingDates.put("checkout", configPropFileData.getBookingDateCheckOut());
        // Add bookingdates to the main jsonObject
        jsonObject.put("bookingdates", bookingDates);
        jsonObject.put("additionalneeds", configPropFileData.getAdditionalNeeds());

        response = given().log().all().header("Content-type", "application/json")
                .and()
                .body(jsonObject.toString())
                .when()
                .post(configPropFileData.getServer()+configPropFileData.getEndpoint())
                .then()
                .extract().response();

        //Extracting data
        bookingid = response.jsonPath().getString("bookingid");
        postedFName = response.jsonPath().getString("booking.firstname");
        postedLName = response.jsonPath().getString("booking.lastname");
        totalPrice = response.jsonPath().getString("booking.totalprice");
        depositPaid = response.jsonPath().getString("booking.depositpaid");
        bookingDatesCheckIn = response.jsonPath().getString("booking.bookingdates.checkin");
        bookingDatesCheckOut = response.jsonPath().getString("booking.bookingdates.checkout");
        additionalneeds = response.jsonPath().getString("booking.additionalneeds");

        System.out.println("Deposit " +depositPaid);
    }

    @Then("^Status code should be (\\d+)$")
    public void status_code_should_be(int status){
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^Verify the get call contains same payload data as posted$")
    public void verifyTheGetCallContainsSamePayloadDataAsPosted() {
        Assert.assertEquals(postedFName, configPropFileData.getFirstName());
        Assert.assertEquals(postedLName, configPropFileData.getLastName());
        Assert.assertEquals(totalPrice, configPropFileData.getTotalPrice());
        Assert.assertTrue(Boolean.parseBoolean(depositPaid));
        Assert.assertEquals(bookingDatesCheckIn, configPropFileData.getBookingDateCheckIn());
        Assert.assertEquals(bookingDatesCheckOut, configPropFileData.getBookingDateCheckOut());
        Assert.assertEquals(additionalneeds, configPropFileData.getAdditionalNeeds());
    }
}
