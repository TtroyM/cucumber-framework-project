package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OffersPage {
    private final WebDriver driver;

    private final By search = By.xpath("//input[@type='search']");
    private final By productName = By.cssSelector("table tbody tr td:nth-child(1)");
    private final By noDataCell = By.cssSelector("table tbody td.text-center");

    public OffersPage(WebDriver driver) {
        this.driver = driver;
    }



    public String getProductName() {
        if (isNoDataPresent()) return "No data";

        List<WebElement> products = driver.findElements(productName);
        if (products.isEmpty()) return "No data";

        return products.get(0).getText().trim();
    }

    public void searchItemAndWait(String name) {
        WebElement box = driver.findElement(search);
        box.clear();
        box.sendKeys(name);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> {
            // No data case
            List<WebElement> noData = d.findElements(noDataCell);
            if (!noData.isEmpty() && noData.get(0).getText().trim().equalsIgnoreCase("No data")) {
                return true;
            }

            // Result exists case
            List<WebElement> cells = d.findElements(productName);
            return !cells.isEmpty();
        });
    }

    private boolean isNoDataPresent() {
        List<WebElement> noData = driver.findElements(noDataCell);
        return !noData.isEmpty() && noData.get(0).getText().trim().equalsIgnoreCase("No data");
    }


}
