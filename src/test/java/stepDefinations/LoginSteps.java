package stepDefinations;

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import environment.DriverSelector;
import environment.DriverConfiguration;
import static org.junit.Assert.*;

import java.net.MalformedURLException;

public class LoginSteps {
    private LoginPage loginPage;
    private WebDriver driver;

    // Constructor to initialize the driver using DriverSelector
    public LoginSteps() throws MalformedURLException {
        DriverSelector driverSelector = new DriverSelector();
        String driverConfig = System.getProperty("driverConfig");
        DriverConfiguration driverConfiguration = driverSelector.getDriver(driverConfig);
        this.driver = driverConfiguration.setUpConfiguration();
        this.loginPage = new LoginPage(driver);
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {
        try {
            
            assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        } catch (Exception e) {
            throw new RuntimeException("Failed to navigate to login page: " + e.getMessage());
        }
    }

    @When("the user enters valid credentials {string} and {string}")
    public void the_user_enters_valid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @When("the user enters invalid credentials {string} and {string}")
    public void the_user_enters_invalid_credentials(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    @When("the user enters blank credentials")
    public void the_user_enters_blank_credentials() {
        loginPage.clickLogin();
    }

    @Then("the user should be redirected to the Products page")
    public void the_user_should_be_redirected_to_the_products_page() {
        assertTrue("User not redirected to the Products page.", loginPage.isOnProductsPage());
    }

    @Then("the user should see an error message {string}")
    public void the_user_should_see_an_error_message(String expectedMessage) {
        assertEquals(expectedMessage, loginPage.getErrorMessage());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}