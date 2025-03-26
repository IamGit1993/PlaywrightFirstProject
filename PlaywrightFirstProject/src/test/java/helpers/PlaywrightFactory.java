package helpers;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
    
    private static Playwright playwright;  // Playwright instance for managing browser automation
    private static Browser browser;  // Browser instance to launch and interact with Chromium
    private static BrowserContext context;  // Browser context for managing sessions and cookies
    private static Page page;  // Page instance for interacting with web pages

    /**
     * Initializes the Playwright browser instance.
     * @return Page - The Playwright page object for test execution.
     */
    public static Page initBrowser() {
        playwright = Playwright.create();  // Creating a Playwright instance
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setHeadless(false)  // Runs the browser in visible mode (for debugging)
                .setSlowMo(500)  // Adds a delay of 500ms to each action for better visibility
        );
        context = browser.newContext();  // Creates a new browser context (session)
        page = context.newPage();  // Opens a new page in the browser
        return page;  // Returns the Playwright Page object
    }

    /**
     * Closes the Playwright browser instance.
     */
    public static void closeBrowser() {
        if (playwright != null) {  // Checks if Playwright instance is running
            playwright.close();  // Closes the Playwright instance and releases resources
        }
    }
}