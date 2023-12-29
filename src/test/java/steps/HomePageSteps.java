package steps;

import io.cucumber.java.en.When;
import models.pages.HomePage;
import models.pages.ProductListingPage;

public class HomePageSteps {

    HomePage homePage;
    ProductListingPage productListingPage;

    @When("user searches {string} in the search bar")
    public void search(String keyword) {
        homePage = new HomePage();
        productListingPage = new ProductListingPage();

        homePage.rejectCookies();
        homePage.search(keyword);
        productListingPage.checkSearchResults(keyword);
    }


}
