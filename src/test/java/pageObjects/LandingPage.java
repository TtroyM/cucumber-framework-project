package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {
    public WebDriver driver;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }
    By search = By.cssSelector("input.search-keyword");
    By productName = By.cssSelector("h4.product-name");
    By topDealsLink = By.linkText("Top Deals");
    By increment = By.cssSelector("a.increment");
    By addToCart = By.cssSelector(".product-action button");
    By noResultsMsg = By.xpath("//h2[contains(.,'Sorry, no products matched your search!')]");


    public String getProductName() {
        if (!driver.findElements(noResultsMsg).isEmpty()) return "No data";
        return driver.findElement(productName).getText().trim();
    }

    public void selectTopDeals() {
        driver.findElement(topDealsLink).click();
    }

    public String getTitleLandingPage() {
        return driver.getTitle();
    }

    public void incrementQuantity(int quantity) {

        int clicks = quantity - 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        for (int i = 0; i < clicks; i++) {
            for (int attempt = 0; attempt < 3; attempt++) {
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(increment)).click();
                    break; // success, exit retry loop
                } catch (StaleElementReferenceException e) {
                    // try again; DOM likely re-rendered
                }
            }
        }
    }

    public void addToCart() {
        driver.findElement(addToCart).click();
    }

    public void searchItemAndWait(String name) {
        WebElement box = driver.findElement(search);
        box.clear();
        box.sendKeys(name);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> {
            if (!d.findElements(noResultsMsg).isEmpty()) return true;

            WebElement firstName = d.findElement(productName);
            return firstName.getText().toLowerCase().contains(name.toLowerCase());
        });
    }


}
