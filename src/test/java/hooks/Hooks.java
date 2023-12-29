package hooks;

import browsers.Browser;
import browsers.BrowserFactory;
import browsers.DriverFactory;
import config.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class Hooks {

    BrowserFactory browserFactory = new BrowserFactory();
    Browser browser = Config.BROWSER;
    String baseURL = Config.BASE_URL;

    @Before("@ui")
    public void beforeScenario() throws Exception {
        DriverFactory.getInstance().setDriver(browserFactory.initBrowser(browser));
        WebDriver driver = DriverFactory.getInstance().getDriver();
        driver.manage().window().maximize();
    }

    @After("@ui")
    public void afterScenario(Scenario scenario) {
        if (scenario.isFailed())
            attachScreenshot();

        DriverFactory.getInstance().closeBrowser();
    }

    public void attachScreenshot() {
        byte [] screenshot = ((TakesScreenshot) DriverFactory.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshot));
    }
}
