package tests;

import com.microsoft.playwright.Page;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.DashBoardPage;
import pages.LoginPage;
import helpers.PlaywrightFactory;
import helpers.ScreenshotHelper;
import utils.ConfigReader;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import reports.ExtentManager;

public class LoginTest {
	private Page page; // Playwright page instance to interact with the browser
	private LoginPage loginPage; // LoginPage object to perform login actions
	private ExtentReports extent; // ExtentReports instance for reporting
	private ExtentTest test; // ExtentTest instance for logging test steps

	/**
	 * Initializes the Extent Reports instance before the test suite starts.
	 */
	@BeforeSuite
	public void setupReport() {
	    extent = ExtentManager.getReportInstance(); // Fetches the ExtentReports instance
	}

	/**
	 * Sets up the browser and initializes page objects before any test class runs.
	 */
	@BeforeClass
	public void setup() {
	    page = PlaywrightFactory.initBrowser(); // Launches the Playwright browser instance
	    loginPage = new LoginPage(page); // Initializes the login page object
	}

	@Test
	public void testValidLogin() {
	    try {
	        // Create a new test in the Extent Report
	        test = extent.createTest("Test Valid Login");

	        // Log information about the expected behavior
	        test.info("User should be navigated to desired URL");

	        // Navigate to the login page
	        loginPage.navigateToLogin("https://practicetestautomation.com/practice-test-login/");
	        test.pass("User is able to navigate to the desired URL");

	        // Log information about entering credentials
	        test.info("User should be able to insert a valid ID and Password");

	        // Enter valid login credentials
	        loginPage.login("student", "Password123");
	        test.pass("User is able to insert valid ID and Password");

	        // Log information about successful login validation
	        test.info("User should log in successfully");

	        // Verify if the login was successful
	        boolean loginSuccess = page.locator("//h1[.='Logged In Successfully']").isVisible();
	        Assert.assertTrue(loginSuccess, "Login failed!");
	        test.pass("User is able to log in successfully");

	        // Capture a screenshot on success
	        ScreenshotHelper.takeScreenshot(test, page, "pass");
	    } catch (Exception e) {
	        // Capture a screenshot on failure and rethrow the exception
	    	test.fail("User is not able to login successfully");
	        ScreenshotHelper.takeScreenshot(test, page, "fail");
	        throw e;
	    }
	}

	@Test
	public void testInvalidLogin() {
	    try {
	        // Create a new test in the Extent Report
	        test = extent.createTest("Test Invalid Login");

	        // Log information about the expected behavior
	        test.info("User should be navigated to the desired URL");

	        // Navigate to the login page
	        loginPage.navigateToLogin("https://practicetestautomation.com/practice-test-login/");
	        test.pass("User is able to navigate to the desired URL");

	        // Log information about entering credentials
	        test.info("User should be able to insert an invalid ID and Password");

	        // Enter invalid login credentials
	        loginPage.login("invalidUser", "invalidPass");
	        test.pass("User is able to insert invalid ID and Password");

	        // Log information about expected login failure
	        test.info("User should not be able to log in successfully");

	        // Verify if the error message is displayed for an invalid login attempt
	        boolean errorDisplayed = loginPage.verifyUnsuccessfullLogin();
	        Assert.assertTrue(errorDisplayed, "Error message not displayed for invalid login!");
	        test.pass("Error message displayed correctly for invalid login");

	        // Capture a screenshot on success
	        ScreenshotHelper.takeScreenshot(test, page, "pass");
	    } catch (Exception e) {
	        // Capture a screenshot on failure and re throw the exception
	    	test.fail("With invalid login crediential still user is able to login");
	        ScreenshotHelper.takeScreenshot(test, page, "fail");
	        throw e;
	    }
	}

	/**
	 * Closes the browser session after all tests in the class have executed.
	 */
	@AfterClass
	public void tearDown() {
	    PlaywrightFactory.closeBrowser(); // Closes the Playwright browser instance
	}

	/**
	 * Flushes the Extent Reports data to generate the final report after the test suite execution.
	 */
	@AfterSuite
	public void flushReport() {
	    extent.flush(); // Writes the test execution data to the Extent Report
	}
}
