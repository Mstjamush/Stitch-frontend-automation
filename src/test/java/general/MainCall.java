package general;


import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;

import objects.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import static config.ConfigProperties.Environment;

public class MainCall {

    static ExtentReports extent;
    public static String caseID;
    public static WebDriverFactory webDriverFactory = new WebDriverFactory();
    public static SeleniumFunctions seleniumFunctions = new SeleniumFunctions();
    public static LandingPage landingPage = new LandingPage();


    public static ExtentReports startReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/reports/ExtentReport.html", true);
             extent.addSystemInfo("Environment", Environment);
        extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
        return extent;
    }

    public static WebDriverWait waitCall() {
        return new WebDriverWait(WebDriverFactory.getDriver(), 15);

    }

    public static ExtentReports getExtentReport() {
        if (extent != null) {
            return extent;
        } else {
            throw new IllegalStateException("Extent Report object not initialized");
        }
    }


}

