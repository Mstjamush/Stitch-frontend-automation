package general;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

import static config.ConfigProperties.*;

public class WebDriverFactory {
    static WebDriver driver;
    static String device = Device; // Change to windows or mac accordingly

    static Boolean headless = Boolean.valueOf(isHeadless);

    public static final String USERNAME = "alvinakamran1";
    public static final String AUTOMATE_KEY = "J26gKTS1gDw9WVEEcsYx";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static WebDriver getInstance() throws MalformedURLException {
        finishDriver();
        WebDriverManager.chromedriver().setup();
        ChromeOptions op = new ChromeOptions();

        if(headless)
            op.addArguments("--headless");

        switch (Browser)
        {
            case "Chrome":
                // System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                op.addArguments("--start-maximized");
                driver = new ChromeDriver(op);
                break;

            case "IE":
                driver = new InternetExplorerDriver();
                break;

            case "Firefox":
                // System.setProperty("webdriver.gecko.driver", geckoDriverPath);
                op.addArguments("--start-maximized");
                driver = new FirefoxDriver();
                break;

            case "Remote":
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setCapability("browser", "Chrome");
                caps.setCapability("browser_version", "78.0 beta");
                caps.setCapability("os", "Windows");
                caps.setCapability("os_version", "10");
                caps.setCapability("resolution", "1024x768");
                driver = new RemoteWebDriver(new URL(URL), caps);

        }


        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        return driver;
    }



    public static WebDriver getDriver() {
        if (driver != null) {
            return driver;
        }
        else
        {
            throw new IllegalStateException("Driver has not been initialized");
        }
    }


    public static void finishDriver()
    {
        if (driver != null)
        {
            driver.quit();
//            driver = null;
        }
    }

}
