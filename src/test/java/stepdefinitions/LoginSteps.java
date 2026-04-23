package stepdefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import pages.LoginPage;
import utils.DriverFactory;

public class LoginSteps {

    LoginPage page;

    @Given("user is on login page")
    public void open_page() {
        page = new LoginPage(DriverFactory.getDriver());
        page.open();
    }

    @When("user enters username {string} and password {string}")
    public void enter_credentials(String user, String pass) {
        page.enterUsername(user);
        page.enterPassword(pass);
    }

    @When("clicks on login button")
    public void clicks_on_login_button() {
        page.clickLogin(); // ✅ NOW REAL ACTION
    }

    @Then("login result should be {string}")
    public void validate(String expected) {

        boolean actual = page.isSuccess();

        if (expected.equalsIgnoreCase("success")) {
            Assert.assertTrue(actual);
        } else {
            Assert.assertFalse(actual);
        }
    }
}