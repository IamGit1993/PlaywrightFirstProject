package pages;

import com.microsoft.playwright.Page;

public class LoginPage {

	private Page page;
	private String usernameSelector = "//input[@id='username']";
	private String passwordSelector = "//input[@id='password']";
	private String loginButtonSelector = "//button[@id='submit']";
	private String msgUnSuccessfullLogin = "//div[contains(text(),'Your username is invalid!')]";

	public LoginPage(Page page) {
		this.page = page;
	}

	public void navigateToLogin(String url) {
		page.navigate(url);
	}

	public void login(String username, String password) {
		page.fill(usernameSelector, username);
		page.fill(passwordSelector, password);
		page.click(loginButtonSelector);
	}

	public boolean verifyUnsuccessfullLogin() {
		return page.locator(msgUnSuccessfullLogin).isVisible();
	}

}
