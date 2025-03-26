package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent; // Singleton instance of ExtentReports
    private static String reportFolderPath; // Path for storing report files

    /**
     * Gets the instance of ExtentReports.
     * If it does not exist, it initializes and configures it.
     * @return ExtentReports instance
     */
    public static ExtentReports getReportInstance() {
        if (extent == null) {
            createReportFolder(); // Create folders dynamically for reports and screenshots
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFolderPath + "/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter); // Attach the report to ExtentReports
        }
        return extent;
    }

    /**
     * Creates a new folder for storing reports with a timestamped name.
     * Also creates a 'screenshots' subfolder inside the report directory.
     */
    private static void createReportFolder() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()); // Generate a timestamp
        reportFolderPath = System.getProperty("user.dir") + "/Report/ExtentReport_" + timeStamp; // Define report folder path
        new File(reportFolderPath + "/screenshots").mkdirs(); // Create directory structure
    }

    /**
     * Retrieves the folder path where reports and screenshots are stored.
     * @return String - The report folder path
     */
    public static String getReportFolderPath() {
        return reportFolderPath;
    }
}