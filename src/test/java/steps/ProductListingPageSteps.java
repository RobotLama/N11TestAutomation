package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import models.headers.BaseHeader;
import models.pages.ProductListingPage;

public class ProductListingPageSteps {

    ProductListingPage productListingPage;
    BaseHeader baseHeader;

    @And("user adds the {string} product to the basket on the {string} page")
    public void addProductToBasket(String whichProduct, String whichPage) throws Exception {
        productListingPage = new ProductListingPage();
        productListingPage.addProductToBasket(whichProduct, whichPage);
    }

    @Then("the number of products in the basket must be {string}")
    public void checkBasketTotalNum(String expectedBasketTotalNum) {
        baseHeader = new BaseHeader();
        baseHeader.checkBasketTotalNum(expectedBasketTotalNum);
    }

    @And("user selects the {string} option from the {string} filter")
    public void selectOptionFromFilter(String whichOption, String filterName) {
        productListingPage = new ProductListingPage();
        productListingPage.selectOptionFromFilter(whichOption, filterName);
    }

    @And("user selects the {string} option from the sort by menu")
    public void selectOptionFromFilter(String sortOption) {
        productListingPage = new ProductListingPage();
        productListingPage.selectOptionFromSortByMenu(sortOption);
        productListingPage.checkReviews();
    }

    @And("user selects the option as {string} from the {string} filter")
    public void selectOptionAs(String optionName, String filterName) {
        productListingPage = new ProductListingPage();
        productListingPage.selectOptionFromFilterAs(optionName, filterName);
    }

    @Then("all of the products should have {string} badge")
    public void checkCargoBadges(String cargoBadgeText) {
        productListingPage = new ProductListingPage();
        productListingPage.checkCargoBadges(cargoBadgeText);
    }

}
