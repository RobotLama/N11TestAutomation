package models.popups;

import methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductFeaturesPopup {

    private static final By title = By.xpath("//h2[text() = 'Ürün Özelliklerini Seç']");
    private static final By productOptions = By.xpath("//fieldset");
    private static final By addToBasketButton = By.id("js-addBasketSku");
    BasketPopup basketPopup;
    Methods methods;

    public ProductFeaturesPopup() {
        this.basketPopup = new BasketPopup();
        this.methods = new Methods();
    }

    public boolean isProductFeaturesPopupDisplayed() {
        boolean isDisplayed = methods.isElementVisible(title, 3);
        return isDisplayed;
    }

    public void selectProductOptions(String productName) throws Exception {
        List<WebElement> options = methods.findElements(productOptions);

        if (!options.isEmpty()) {
            for (WebElement element : options) {
                WebElement firstOption = element.findElement(By.xpath(".//label[@class = 'skus-item'][1]"));
                methods.clickToElement(firstOption);
            }
            methods.clickToBy(addToBasketButton);
            basketPopup.isProductAddedToBasket(productName);
        }
    }
}
