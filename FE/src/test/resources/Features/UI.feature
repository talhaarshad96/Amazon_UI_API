@FrontEndTest
Feature: Test scenario for amazon

  Scenario: Verify price on search, product and total summary on amazon.com
    Given User navigates to amazon url
      And User perform captcha and close the location alert
    Then User verifies text "Today's Deals" on search result page
    When search for product "Toys"
      And User gets price of the product on search page for item 1
      And User select item 1
    Then User verifies text "Back to results" on product page
      And User gets price of the product on product page for item 1
    #now click AddToCart
      And User clicks on Add to Cart
    Then User verifies text "Added to Cart" on product page for cart
    #And User clicks on search button
    When search for product "Toys"
      And User gets price of the product on search page for item 2
      And User select item 2
    Then User verifies text "Back to results" on product page
      And User gets price of the product on product page for item 2
      And User clicks on Add to Cart
    Then User verifies text "Added to Cart" on product page for cart
    #now Total Summary
    Then User verifies the prices of products 1 and 2 on Search page, product page, Total Summary page

