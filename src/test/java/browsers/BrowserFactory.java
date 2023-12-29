package browsers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import static browsers.Browser.supportedBrowsers;

public class BrowserFactory {

    public WebDriver initBrowser(Browser browser) throws Exception {

        WebDriver driver = null;

        switch (browser) {
            case CHROME:
                ChromeOptions options = new ChromeOptions();
                options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(options);
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            default:
                throw new Exception(browser + " is not supported or the browser name was written incorrect!\n" +
                        "Supported browsers: " + supportedBrowsers());
        }
        return driver;
    }
}
