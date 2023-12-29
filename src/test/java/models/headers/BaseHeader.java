package models.headers;

import methods.Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class BaseHeader {

    private static final By searchBar = By.id("searchData");
    private static final By basketTotalNum = By.className("basketTotalNum");
    Methods methods;

    public BaseHeader() {
        this.methods = new Methods();
    }

    public void searchProduct(String keyword) {
        WebElement searchBox = methods.findElement(searchBar);
        methods.type(keyword, true, searchBox);
        searchBox.sendKeys(Keys.ENTER);
    }

    public void checkBasketTotalNum(String expectedBasketTotalNum) {
        String actualBasketTotalNum = methods.findElement(basketTotalNum).getText();
        Assert.assertEquals(actualBasketTotalNum, expectedBasketTotalNum);
    }
}