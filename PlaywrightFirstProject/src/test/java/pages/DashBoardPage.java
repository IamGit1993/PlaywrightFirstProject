package pages;

import com.microsoft.playwright.Page;

public class DashBoardPage {
	private Page page;

	private String dashBoardHeaderName = "//h1[.='Logged In Successfully']";

	public DashBoardPage(Page page) {
		this.page = page;
	}

	public boolean verifyHeaderIsVisible() {
		return page.locator(dashBoardHeaderName).isVisible();
	}

}
