package steps;

import io.cucumber.java.en.Given;
import methods.Methods;

public class BaseSteps {

    Methods methods;

    @Given("user is on the {string} Page")
    public void goToPage(String pageName) throws Exception {
        methods = new Methods();
        methods.goToPage(pageName);
    }

}
