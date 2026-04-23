package stepdefinitions;

import io.cucumber.java.en.*;
import pages.LoginPage;
import utils.*;

import org.openqa.selenium.WebDriver;

public class ExcelDrivenSteps {

    String excelPath = "src/test/resources/testdata/loginData.xlsx";

    @Given("browser is launched")
    public void launch_browser() {
        // handled per iteration
    }

    @When("user logs in using Excel data")
    public void login_with_excel() {

        int rows = ExcelReader.getRowCount(excelPath, "Sheet1");

        for (int i = 1; i <= rows; i++) {

            WebDriver driver = null;

            try {
                driver = DriverFactory.initDriver();
                LoginPage page = new LoginPage(driver);

                page.open();

                String username = ExcelReader.getData(excelPath, "Sheet1", i, 0);
                String password = ExcelReader.getData(excelPath, "Sheet1", i, 1);
                String expected = ExcelReader.getData(excelPath, "Sheet1", i, 2);

                // ✅ NEW CLEAN FLOW
                page.enterUsername(username);
                page.enterPassword(password);
                page.clickLogin();

                boolean actual = page.isSuccess();

                System.out.println(
                        "User: " + username +
                                " | Expected: " + expected +
                                " | Actual: " + actual);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (driver != null) {
                    driver.quit();
                }
            }
        }
    }

    @Then("performance test is executed")
    public void run_performance_test() {
        JMeterRunner.run("jmeter/testplans/get_test.jmx");
    }
}