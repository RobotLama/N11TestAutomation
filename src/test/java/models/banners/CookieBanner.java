package models.banners;

import methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CookieBanner {

    private static final By shadowRoot = By.tagName("efilli-layout-dynamic");
    private static final By rejectButtonLocator = By.cssSelector("[data-name = 'Reject Button']");
    Methods methods;

    public CookieBanner() {
        this.methods = new Methods();
    }

    public void rejectCookies() {
        WebElement rejectButton = methods.getShadowRootElement(shadowRoot, rejectButtonLocator);
        methods.clickToElement(rejectButton);
    }

}
