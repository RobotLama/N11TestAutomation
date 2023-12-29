package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.pages.SellersPage;
import utils.TestContext;

public class SellersPageSteps {

    SellersPage sellersPage;
    TestContext testContext;

    public SellersPageSteps(TestContext testContext) {
        this.testContext = testContext;
    }

    @When("user clicks to {string} button")
    public void clickTo(String buttonName) {
        sellersPage = new SellersPage(testContext);
        sellersPage.clickTo(buttonName);
    }

    @And("user clicks to the {string} letter to filter sellers")
    public void filterSellersStartsWith(String letter) {
        sellersPage = new SellersPage(testContext);
        sellersPage.filterSellersStartsWith(letter);
    }

    @And("user clicks to a seller randomly")
    public void clickToASellerRandomly() {
        sellersPage = new SellersPage(testContext);
        sellersPage.clickToASellerRandomly();
    }

    @Then("user should see that navigated to the desired seller's page")
    public void shouldNavigateToDesiredSeller() {
        sellersPage = new SellersPage(testContext);
        sellersPage.shouldNavigateToDesiredSeller();
    }

}
