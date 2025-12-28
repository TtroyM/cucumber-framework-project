package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    private WebDriver driver;
    private Properties props;

    public WebDriver getDriver() throws IOException {
        if (driver == null) {
            props = loadProps();

            String url = props.getProperty("baseUrl");
            String browserFromProps = props.getProperty("browser", "chrome");
            String browserFromMaven = System.getProperty("browser");
            String browser = (browserFromMaven != null) ? browserFromMaven : browserFromProps;

            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
            driver.manage().window().maximize();
            driver.get(url);
        }
        return driver;
    }

    private Properties loadProps() throws IOException {
        Properties prop = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("global.properties")) {
            if (is == null) {
                throw new IOException("global.properties not found in src/test/resources");
            }
            prop.load(is);
        }
        return prop;
    }
}
