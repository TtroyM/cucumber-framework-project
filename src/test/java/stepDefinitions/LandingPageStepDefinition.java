package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObjects.LandingPage;
import utils.TestContextSetup;


public class LandingPageStepDefinition {

    private final LandingPage landingPage;
    private final TestContextSetup testContextSetup;


    public LandingPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
        this.landingPage = testContextSetup.pageObjectManager.getLandingPage();
    }

    //Both Features
    @Given("the user is on GreenKart Landing page")
    public void user_is_on_green_cart_landing_page() {

        landingPage.getTitleLandingPage();
        Assert.assertTrue(landingPage.getTitleLandingPage().contains("GreenKart"));
    }


    //Checkout and searchProduct
    @When("^the user searches for (.+) and extracts actual name of product$")
    public void user_searches_with_shortname_and_extracts_actual_name_of_product(String shortName) {

        landingPage.searchItemAndWait(shortName);

        String fullName = landingPage.getProductName();

        Assert.assertNotNull(fullName, "Product name was null after searching: " + shortName);
        Assert.assertFalse(fullName.equalsIgnoreCase("No data"),
                "No product data returned for search term: " + shortName);

        testContextSetup.landingPageProductName = fullName.split("-")[0].trim();
    }

    //Checkout
    @When("^the user adds (.+) items of the selected product to cart$")
    public void added_items_of_the_selected_product_to_cart(String quantity) {

        landingPage.incrementQuantity(Integer.parseInt(quantity));
        landingPage.addToCart();
    }

}
