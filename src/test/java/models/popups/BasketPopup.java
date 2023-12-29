package models.popups;

import methods.Methods;
import org.openqa.selenium.By;
import org.testng.Assert;

public class BasketPopup {

    private static final By addedProductNameOnPopupLocator = By.xpath("//div[@class = 'text']//span");
    private static final By goBasketButton = By.xpath("//div[@class = 'items-action']");
    Methods methods;

    public BasketPopup() {
        this.methods = new Methods();
    }

    public void isProductAddedToBasket(String addedProductName) throws Exception {
        if (isBasketPopupVisible()) {
            String addedProductNameOnPopup = methods.findElement(addedProductNameOnPopupLocator).getText();
            Assert.assertEquals(addedProductNameOnPopup, addedProductName, "The added product name should have been " + addedProductName + " but came as " + addedProductNameOnPopup);
        } else {
            throw new Exception("After clicking the 'Sepete Ekle' button, the pop-up stating 'Ürün sepetinize eklendi' was not displayed!");
        }
    }

    public boolean isBasketPopupVisible() {
        methods.wait(3);
        return methods.isElementVisible(goBasketButton);
    }

}
