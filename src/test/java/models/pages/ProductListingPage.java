package models.pages;

import methods.Methods;
import models.popups.BasketPopup;
import models.popups.ProductFeaturesPopup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utils.StringUtils;

import java.util.*;

public class ProductListingPage {

    private static final By addToBasketButtonLocator = By.xpath(".//span[@class = 'btnBasket']");
    private static final By productNameLocator = By.xpath(".//h3[@class = 'productName']");
    private static final By productNamesLocator = By.xpath("//li[@class = 'column ']//h3");
    private static final By resultTextLocator = By.xpath("//div[@class = 'resultText ']/h1");
    private static final By sortByMenuLocator = By.xpath("//div[@class = 'customSelectBox']");
    Methods methods;
    ProductFeaturesPopup productFeaturesPopup;
    BasketPopup basketPopup;

    public ProductListingPage() {
        this.methods = new Methods();
        this.productFeaturesPopup = new ProductFeaturesPopup();
        this.basketPopup = new BasketPopup();
    }

    public void checkSearchResults(String keyword) {
        String resultText = methods.findElement(resultTextLocator).getText();
        Assert.assertTrue(resultText.toLowerCase(Locale.ENGLISH).contains(keyword.toLowerCase(Locale.ENGLISH)));
    }

    public void addProductToBasket(String whichProduct, String whichPage) throws Exception {
        List<WebElement> products = getListOfProductsOnPageNo(whichPage);
        WebElement product = getProduct(whichProduct, products);
        clickToAddToBasketButton(product);
    }

    public void checkProductNames(String keyword) {
        List<WebElement> productNames = methods.findElements(productNamesLocator);

        for (WebElement element: productNames) {
            Assert.assertTrue(element.getText().contains(keyword), "The product name should have contained " + keyword + " but came as " + element.getText());
        }
    }

    public List<WebElement> getListOfProductsOnPageNo(String whichPage) {
        Map<String, String> pageLocators = new HashMap<>();
        pageLocators.put("first", "//li[@class = 'column ' and not(@data-ipg)]");
        pageLocators.put("second", "//li[@class = 'column ' and @data-ipg = '2']");

        List<WebElement> products = methods.findElements(By.xpath(pageLocators.get(whichPage)));
        return products;
    }

    public WebElement getProduct(String whichProduct, List<WebElement> products) {
        WebElement product = null;

        switch (whichProduct) {
            case "first":
                product = products.getFirst();
                break;
            case "last":
                product = products.getLast();
                break;
        }
        return product;
    }

    public void clickToAddToBasketButton(WebElement product) throws Exception {
        String productName = product.findElement(productNameLocator).getText();
        WebElement addToBasketButton = product.findElement(addToBasketButtonLocator);
        methods.clickToElement(addToBasketButton);

        if (productFeaturesPopup.isProductFeaturesPopupDisplayed()) {
            productFeaturesPopup.selectProductOptions(productName);
        } else {
            basketPopup.isProductAddedToBasket(productName);
        }
    }

    public void selectOptionFromFilter(String whichOption, String filterName) {
        String filterSectionLocator = "//section[@data-hdfl = \'" + getFilterLocators().get(filterName) + "\']";
        WebElement filterSection = methods.findElement(By.xpath(filterSectionLocator));

        if (!isSectionOpen(filterSection))
            methods.clickToElement(filterSection);

        List<WebElement> options = methods.findElements(By.xpath("//section[@data-hdfl = \'" + getFilterLocators().get(filterName) + "\']//div[contains(@class, 'filterItem')]/a"));
        WebElement option = null;

        switch (whichOption) {
            case "first":
                option = options.get(0);
                break;
            case "second":
                option = options.get(1);
                break;
        }
        methods.clickToElement(option);
    }

    public void selectOptionFromFilterAs(String optionName, String filterName) {
        String filterSectionLocator = "//section[@data-hdfl = \'" + getFilterLocators().get(filterName) + "\']";
        WebElement filterSection = methods.findElement(By.xpath(filterSectionLocator));

        if (!isSectionOpen(filterSection))
            methods.clickToElement(filterSection);

        WebElement option;
        if (!filterName.equalsIgnoreCase("Marka"))
            option = methods.findElement(By.xpath(filterSectionLocator + "//span[text() = \'" + optionName + "\']"));
        else option = methods.findElement(By.xpath(filterSectionLocator + "//a[@title = \'" + optionName + "\']"));

        methods.clickToElement(option);
    }

    public Map<String, String> getFilterLocators() {
        Map<String, String> filterSections = new HashMap<>();
        filterSections.put("Marka", "m");
        filterSections.put("Kargo Seçenekleri", "shpms");

        return filterSections;
    }

    public void selectOptionFromSortByMenu(String sortOption) {
        clickTo("sortByMenu");

        String optionLocator = "//div[@class = 'customSelectBox']//div[@class = 'all-items']/div[@data-value = \'" + getSortOptions().get(sortOption) + "\']";
        WebElement option = methods.findElement(By.xpath(optionLocator));
        methods.clickToElement(option);
    }

    public Map<String, String> getSortOptions() {
        Map<String, String> sortOptions = new HashMap<>();
        sortOptions.put("Akıllı Sıralama", "NUMARA11");
        sortOptions.put("Azalan fiyat", "PRICE_LOW");
        sortOptions.put("Satış miktarı", "SALES_VOLUME");
        sortOptions.put("Yorum sayısı", "REVIEWS");
        sortOptions.put("Yeni ürün", "NEWEST");
        sortOptions.put("Ürün Notu", "REVIEW_RATE");
        sortOptions.put("Mağaza Puanı", "SELLER_GRADE");

        return sortOptions;
    }

    public void clickTo(String element) {

        switch (element) {
            case "sortByMenu":
                WebElement sortByMenu = methods.findElement(sortByMenuLocator);
                methods.clickToElement(sortByMenu);
        }
    }

    public void checkReviews() {
        List<WebElement> products = getListOfProductsOnPageNo("first");
        List<Integer> reviewRatings = new ArrayList<Integer>();
        for (WebElement element: products) {
            String reviewRatingCount = element.findElement(By.xpath(".//span[@class = 'ratingText']")).getText();
            reviewRatingCount = StringUtils.removeBrackets(reviewRatingCount);
            reviewRatings.add(Integer.valueOf(reviewRatingCount));
        }
        StringUtils.isSorted(reviewRatings, true);
    }

    public void checkCargoBadges(String expectedCargoBadgeText) {
        List<WebElement> products = getListOfProductsOnPageNo("first");

        for (WebElement element: products) {
            String actualCargoBadgeText = element.findElement(By.xpath(".//span[@class = 'cargoBadgeText']")).getText();
            Assert.assertEquals(actualCargoBadgeText, expectedCargoBadgeText.toUpperCase(), "The actual cargo badge is not equal to expected cargo badge!\n"
                                                                                                   + "Actual Cargo Badge: " + actualCargoBadgeText
                                                                                                   + " Expected Cargo Badge: " + expectedCargoBadgeText);
        }
    }

    public boolean isSectionOpen(WebElement section) {
        return section.getAttribute("class").contains("open");
    }

}
