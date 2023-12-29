package methods;

import browsers.DriverFactory;
import config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Methods {

    protected WebDriver driver;
    protected JavascriptExecutor jsDriver;
    protected FluentWait<WebDriver> fluentWait;
    String baseURL = Config.BASE_URL;

    public Methods() {
        this.driver = DriverFactory.getInstance().getDriver();
        jsDriver = (JavascriptExecutor) driver;
        fluentWait = setFluentWait(10);
    }

    public FluentWait<WebDriver> setFluentWait(int timeout) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(NotFoundException.class);
    }

    public WebElement waitForClickableOf(WebElement element) {
        return fluentWait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement findElement(By by) {
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public List<WebElement> findElements(By by) {
        List<WebElement> elements = driver.findElements(by);
        return elements;
    }

    public WebElement getShadowRootElement(By shadowRoot, By searchElement) {
        SearchContext shadowElement = findElement(shadowRoot).getShadowRoot();
        WebElement element = shadowElement.findElement(searchElement);
        return element;
    }

    public void scrollElementCenter(By by) {
        WebElement element = findElement(by);
        jsDriver.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});", element);
        try {
            Thread.sleep(Duration.ofSeconds(3));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isElementVisible(By by) {
        try {
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementVisible(By by, int timeout) {
        try {
            setFluentWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickToElement(WebElement element) {
        highlightElement(element);
        waitForClickableOf(element);
        element.click();
    }

    public void clickToBy(By by) {
        clickToElement(waitForClickableOf(findElement(by)));
    }

    public void type(String text, boolean isClear, WebElement element) {
        highlightElement(element);
        waitForClickableOf(element);
        if (isClear)
            element.clear();
        element.sendKeys(text);
    }

    public void highlightElement(WebElement element) {
        jsDriver.executeScript("arguments[0].style.background = 'blue';", element);
    }

    public void goToPage(String pageName) throws Exception {
        Map<String, String> urls = new HashMap<>();
        urls.put("Home", baseURL);
        urls.put("Sellers", baseURL + "/magazalar");

        if (urls.containsKey(pageName)) {
            driver.get(urls.get(pageName));
        } else {
            throw new Exception("URL of the " + pageName + " Page is not included in the method!");
        }
    }

    public void wait(int timeout) {
        try {
            Thread.sleep(Duration.ofSeconds(timeout));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}