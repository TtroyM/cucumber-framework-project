package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.TestContextSetup;

import java.io.File;
import java.io.IOException;


public class Hooks {
    private final TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    @After
    public void tearDown() {
        // Code to close the browser or clean up after tests
        WebDriver driver = testContextSetup.driver;
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterStep
    public void addScreenshotOnFailure(Scenario scenario) throws IOException {

        WebDriver driver = testContextSetup.driver;

        if (driver != null && scenario.isFailed()) {
            File sourcePath = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "failed_step");
        }
    }

}
