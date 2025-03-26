package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFolderPath;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
            createReportFolder(); // Create folders dynamically
            ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFolderPath + "/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    private static void createReportFolder() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        reportFolderPath = System.getProperty("user.dir") + "/Report/ExtentReport_" + timeStamp;
        new File(reportFolderPath + "/screenshots").mkdirs(); // Create folder structure
    }

    public static String getReportFolderPath() {
        return reportFolderPath;
    }
}
