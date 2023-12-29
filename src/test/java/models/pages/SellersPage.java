package models.pages;

import methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.TestContext;

import java.util.List;
import java.util.Random;

public class SellersPage {

    private static final By allSellersButtonLocator = By.xpath("//h3[text() = 'Tüm Mağazalar']");
    private static final By allSellersList = By.xpath("//div[@class = 'sellerListHolder']//i/../..//ul/li/a");
    Methods methods;
    TestContext testContext;


    public SellersPage(TestContext testContext) {
        this.methods = new Methods();
        this.testContext = testContext;
    }

    public void clickTo(String elementName) {
        WebElement element = null;

        switch (elementName) {
            case "Tüm Mağazalar":
                element = methods.findElement(allSellersButtonLocator);
        }
        methods.clickToElement(element);
    }

    public void filterSellersStartsWith(String letter) {
        WebElement letterButton = methods.findElement(By.xpath("//span[@data-has-seller = \'" + letter + "\']"));
        methods.clickToElement(letterButton);
        Assert.assertTrue(methods.isElementVisible(By.xpath("//span[@class = 'infoText']/i[contains(text(), \'" + letter + "\')]")));
    }

    public void clickToASellerRandomly() {
        Random random = new Random();
        List<WebElement> allSellers = methods.findElements(allSellersList);
        String selectedElementIndex = String.valueOf(random.nextInt(allSellers.size()));
        String selectedSellerLocator = "//div[@class = 'sellerListHolder']//i/../..//ul/li[" + selectedElementIndex + "]/a";
        WebElement selectedSeller = methods.findElement(By.xpath(selectedSellerLocator));

        methods.scrollElementCenter(By.xpath(selectedSellerLocator));
        String selectedSellerUrl = selectedSeller.getAttribute("href");
        testContext.selectedSellerUrl = selectedSellerUrl;
        methods.clickToElement(selectedSeller);
    }

    public void shouldNavigateToDesiredSeller() {
        String selectedSellerUrl = testContext.selectedSellerUrl;
        String actualUrl = methods.getCurrentUrl();
        Assert.assertEquals(actualUrl, selectedSellerUrl, "The selected seller URL is not equal to current URL!\n"
                                                                 + "Selected Seller URL: " + selectedSellerUrl
                                                                 + " Current URL: " + actualUrl);
    }
}
