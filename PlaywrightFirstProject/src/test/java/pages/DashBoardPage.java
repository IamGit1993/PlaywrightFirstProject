package pages;

import com.microsoft.playwright.Page;

public class DashBoardPage {
	private Page page;  // Playwright Page instance for interacting with the Dashboard page

	// Locator for the Dashboard header element
	private String dashBoardHeaderName = "//h1[.='Logged In Successfully']";

	/**
	 * Constructor to initialize the Dashboard page with a Playwright Page instance.
	 * @param page Playwright Page instance
	 */
	public DashBoardPage(Page page) {
		this.page = page;
	}

	/**
	 * Method to verify if the Dashboard header is visible.
	 * @return boolean - true if the header is visible, false otherwise
	 */
	public boolean verifyHeaderIsVisible() {
		return page.locator(dashBoardHeaderName).isVisible();
	}
}