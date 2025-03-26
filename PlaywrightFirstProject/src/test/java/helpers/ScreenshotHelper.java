package helpers;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import reports.ExtentManager;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.MediaEntityBuilder;

public class ScreenshotHelper {
	public static void takeScreenshot(ExtentTest test, Page page, String status) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Define folder paths (absolute for saving, relative for reporting)
        String screenshotFolder = ExtentManager.getReportFolderPath() + "/screenshots";
        String screenshotName = status + "_" + timeStamp + ".png";
        String absolutePath = screenshotFolder + "/" + screenshotName;
        String relativePath = "./screenshots/" + screenshotName; // Relative path for HTML

        // Capture screenshot
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(absolutePath)));

        // Attach screenshot to Extent Report using relative path
        test.info("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());

		if (status.contentEquals("pass")) {
			test.pass("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
		} else {

			test.fail("Screenshot:", MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
		}
	}

}