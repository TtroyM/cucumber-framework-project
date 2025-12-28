package stepDefinitions;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.CheckoutPage;
import utils.TestContextSetup;


public class CheckoutPageStepDefinition {

    private final TestContextSetup testContextSetup;
    private final CheckoutPage checkoutPage;

    public CheckoutPageStepDefinition(TestContextSetup testContextSetup) {

        this.testContextSetup = testContextSetup;
        checkoutPage = testContextSetup.pageObjectManager.getCheckoutPage();
    }

    @Then("the user proceeds to Checkout")
    public void the_user_proceeds_to_checkout() {

        checkoutPage.checkoutItems();
    }

    @Then("^the checkout page should show the selected (.+)$")
    public void the_checkout_page_should_show_the_selected(String productFromExample) {

        String expected = testContextSetup.landingPageProductName;

        if (expected == null || expected.trim().isEmpty() || expected.equalsIgnoreCase("No data")) {
            expected = productFromExample;
        }

        String actual = checkoutPage.getCheckoutProductName();

        Assert.assertNotNull(actual, "Checkout product name was null");
        Assert.assertTrue(
                actual.toLowerCase().contains(expected.toLowerCase()),
                "Expected checkout product to contain: " + expected + " but was: " + actual
        );
    }

    @Then("verify the user has ability to apply promo code")
    public void verify_user_can_apply_promo() {
        Assert.assertTrue(checkoutPage.verifyPromoBtn(), "Promo button is not displayed on checkout page");
    }

    @Then("the user should be able to place the order")
    public void the_user_should_be_able_to_place_the_order() {
        Assert.assertTrue(checkoutPage.verifyPlanOrder(), "Place Order button is not displayed on checkout page");
    }

}
