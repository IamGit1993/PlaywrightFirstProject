package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;  // Playwright Page instance for interacting with the Login page

	// Locators for the login page elements
	private String usernameSelector = "//input[@id='username']";  // Username input field
	private String passwordSelector = "//input[@id='password']";  // Password input field
	private String loginButtonSelector = "//button[@id='submit']";  // Login button
	private String msgUnSuccessfullLogin = "//div[contains(text(),'Your username is invalid!')]";  // Error message for unsuccessful login

	/**
	 * Constructor to initialize the LoginPage with a Playwright Page instance.
	 * @param page Playwright Page instance
	 */
	public LoginPage(Page page) {
		this.page = page;
	}

	/**
	 * Navigates to the given login page URL.
	 * @param url The URL of the login page
	 */
	public void navigateToLogin(String url) {
		page.navigate(url);
	}

	/**
	 * Performs login action by entering username, password, and clicking the login button.
	 * @param username The username to be entered
	 * @param password The password to be entered
	 */
	public void login(String username, String password) {
		page.fill(usernameSelector, username);  // Fill in the username
		page.fill(passwordSelector, password);  // Fill in the password
		page.click(loginButtonSelector);  // Click the login button
	}

	/**
	 * Verifies if an unsuccessful login message is displayed.
	 * @return boolean - true if the error message is visible, false otherwise
	 */
	public boolean verifyUnsuccessfullLogin() {
		return page.locator(msgUnSuccessfullLogin).isVisible();
	}
}