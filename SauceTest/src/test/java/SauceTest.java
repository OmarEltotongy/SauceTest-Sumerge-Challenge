import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class SauceTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        // Set the path to the EdgeDriver executable
        System.setProperty("webdriver.edge.driver", "F:\\Work\\CV For Work\\2- Sumerge\\edgedriver_win64\\msedgedriver.exe");

        // Initialize the EdgeDriver
        driver = new EdgeDriver();
        // Initialize WebDriverWait with a timeout of 10 seconds
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Navigate to the login page
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void testLoginPageElements() {
        // Check if the username input field is present
        WebElement usernameField = findElementWithTimeout(By.id("user-name"));
        Assert.assertTrue(usernameField.isDisplayed(), "Username field is not displayed");

        // Check if the password input field is present
        WebElement passwordField = findElementWithTimeout(By.id("password"));
        Assert.assertTrue(passwordField.isDisplayed(), "Password field is not displayed");

        // Check if the login button is present
        WebElement loginButton = findElementWithTimeout(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Login button is not displayed");
    }

    @Test(dependsOnMethods = "testLoginPageElements")
    public void testValidCredentials() {
        login("standard_user", "secret_sauce");

        // Verify if the inventory container is displayed
        WebElement inventoryContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
        Assert.assertTrue(inventoryContainer.isDisplayed(), "Inventory container is not visible after login");

        // Optionally, verify other elements on the page to ensure successful login
        WebElement menuButton = findElementWithTimeout(By.id("react-burger-menu-btn"));
        Assert.assertTrue(menuButton.isDisplayed(), "Menu button is not displayed on the inventory page");
    }

    @Test(dependsOnMethods = "testLoginPageElements")
    public void testInvalidCredentials() {
        login("invalid_user", "invalid_password");

        // Wait for the error message to be visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-container")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed after invalid login attempt");

        // Check if the error message text is correct
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        String actualErrorMessage = errorMessage.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Error message text does not match expected");
    }

    @Test(dependsOnMethods = "testLoginPageElements")
    public void testEmptyCredentials() {
        login("", "");

        // Wait for the error message to be visible
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error-message-container")));
        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed after empty login attempt");

        // Check for specific error messages based on conditions
        String actualErrorMessage = errorMessage.getText();

        if (actualErrorMessage.contains("Username is required")) {
            Assert.assertTrue(actualErrorMessage.contains("Epic sadface: Username is required"),
                    "Username error message text does not match expected");
        } else if (actualErrorMessage.contains("Password is required")) {
            Assert.assertTrue(actualErrorMessage.contains("Epic sadface: Password is required"),
                    "Password error message text does not match expected");
        } else {
            Assert.fail("Unexpected error message displayed");
        }
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    // Helper method to find element with a timeout
    private WebElement findElementWithTimeout(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    // Helper method to perform login
    private void login(String username, String password) {
        WebElement usernameField = findElementWithTimeout(By.id("user-name"));
        WebElement passwordField = findElementWithTimeout(By.id("password"));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginButton = findElementWithTimeout(By.id("login-button"));
        loginButton.click();
    }
}
