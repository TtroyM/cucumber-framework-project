package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cartBag = By.cssSelector("img[alt='Cart']");
    private final By proceedToCheckout = By.xpath("//button[text()='PROCEED TO CHECKOUT']");
    private final By promoBtn = By.cssSelector(".promoBtn");
    private final By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private final By checkoutProductName = By.cssSelector("p.product-name");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void checkoutItems() {
        wait.until(ExpectedConditions.elementToBeClickable(cartBag)).click();
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckout)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutProductName));
    }

    public String getCheckoutProductName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutProductName))
                .getText().trim();
    }

    public Boolean verifyPromoBtn() {
        return driver.findElement(promoBtn).isDisplayed();
    }

    public Boolean verifyPlanOrder() {
        return driver.findElement(placeOrderBtn).isDisplayed();
    }
}
