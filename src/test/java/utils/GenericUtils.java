package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GenericUtils {
    private final WebDriver driver;

    public GenericUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToWindowWithUrlContains(String urlFragment) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> d.getWindowHandles().size() > 1);

        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl().contains(urlFragment)) {
                return;
            }
        }

        throw new RuntimeException("No window found with URL containing: " + urlFragment);
    }
}
