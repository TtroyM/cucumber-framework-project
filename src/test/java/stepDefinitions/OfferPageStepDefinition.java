package stepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.OffersPage;
import utils.TestContextSetup;


public class OfferPageStepDefinition {

    private String offerPageProductName;
    TestContextSetup testContextSetup;

    public OfferPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    //searchProduct feature
    @Then("^the user searches for (.+) in Offers page to check if product exists$")
    public void user_searches_for_the_same_product_in_offers_page_to_check_if_product_exists(String searchTerm) {

        switchToOffersPage();

        OffersPage offersPage = testContextSetup.pageObjectManager.getOffersPage();
        offersPage.searchItemAndWait(searchTerm);

        offerPageProductName = offersPage.getProductName();
        Assert.assertNotNull(offerPageProductName, "Offer page product name was null");
        Assert.assertFalse(offerPageProductName.equalsIgnoreCase("No data"),
                "No product data returned on offers page for search term: " + searchTerm);
    }

    private void switchToOffersPage() {
        LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
        landingPage.selectTopDeals();

        testContextSetup.genericUtils.switchToWindowWithUrlContains("#/offers");
    }

    //searchProduct feature
    @Then("the user validates product name in offers matches with Landing Page")
    public void validate_product_name_in_offers_matches_with_landing_page() {

        Assert.assertEquals(testContextSetup.landingPageProductName, offerPageProductName);
    }
}
