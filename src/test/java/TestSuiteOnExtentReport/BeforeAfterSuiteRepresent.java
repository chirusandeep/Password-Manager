package TestSuiteOnExtentReport;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BeforeAfterSuiteRepresent
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
     
    @BeforeSuite
    public void setUp()
    {
    	
        htmlReporter = new ExtentHtmlReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);         
    }
     
     
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}