package org.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportUtil {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extentReports;
    Map<Integer, ExtentTest> extentTestMap = new HashMap();
    @BeforeSuite
    public void reportSetUp(){
        htmlReporter=new ExtentHtmlReporter("ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Appium Framework");
        htmlReporter.config().setReportName("Eri Bank Mobile Automation");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports=new ExtentReports();
        extentReports.attachReporter(htmlReporter);
    }

    @AfterSuite
    public void reportTearDown(){
        extentReports.flush();
    }
}
