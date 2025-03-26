package tests;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import helpers.PlaywrightFactory;
import helpers.ScreenshotHelper;
import pages.DashBoardPage;
import pages.LoginPage;
import reports.ExtentManager;

public class DashBoardTest {
	
	private Page page; // Playwright page instance to interact with the browser
	private LoginPage loginPage; // LoginPage object to perform login actions
	private DashBoardPage dashBoardPage; // DashBoardPage object to verify dashboard elements
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
	public void testDashboardPageTitle() {
	    try {
	        // Create a new test in the Extent Report
	        test = extent.createTest("Test DashBoard Page Header");

	        // Log information about the expected behavior
	        test.info("User should be navigated to desired URL");

	        // Navigate to the login page
	        loginPage.navigateToLogin("https://practicetestautomation.com/practice-test-login/");
	        test.pass("User is able to navigate to the desired URL");

	        // Log information about entering credentials
	        test.info("User should be able to insert a valid ID and Password");

	        // Enter valid login credentials
	        loginPage.login("student", "Password13");
	        test.pass("User is able to insert valid ID and Password");

	        // Log information about successful login validation
	        test.info("User should log in successfully");

	        // Verify if the login was successful
	        dashBoardPage=new DashBoardPage(page);
	        boolean loginSuccess =dashBoardPage.verifyHeaderIsVisible(); 
	        Assert.assertTrue(loginSuccess, "Not able to verify page header");
	        test.pass("User is able to verify page header");

	        // Capture a screenshot on success
	        ScreenshotHelper.takeScreenshot(test, page, "pass");
	    } catch (AssertionError e) {
	        // Capture a screenshot on failure and rethrow the exception
	    	test.fail("User is not able to verify page header");
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
