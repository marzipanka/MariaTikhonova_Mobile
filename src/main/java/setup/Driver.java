package setup;

import enums.PropertyFile;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Initialize a driver with test properties
 */

public class Driver extends TestProperties {

    //Properties to be read
    protected static String AUT; //(mobile) app under testing
    protected static String SUT; //site under testing
    protected static String TEST_PLATFORM;
    protected static String DRIVER;
    protected static String DEVICE_NAME;
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;
    private static AppiumDriver driverSingle = null;
    private static WebDriverWait waitSingle;
    protected DesiredCapabilities capabilities;

    //Constructor initializes properties on driver creation
    protected Driver(PropertyFile prop) throws IOException {
        AUT = getProp("aut", prop);
        String t_sut = getProp("sut", prop);
        SUT = t_sut == null ? null : "https://" + t_sut;
        TEST_PLATFORM = getProp("platform", prop);
        DRIVER = getProp("driver", prop).replace("${epam_token}", System.getenv("EPAM_TOKEN"));
        DEVICE_NAME = getProp("udid", prop);
        APP_PACKAGE = getProp("appPackage", prop);
        APP_ACTIVITY = getProp("appActivity", prop);
    }

    /**
     * Initialize driver with appropriate capabilities depending on platform and application
     *
     * @throws Exception
     */
    protected void prepareDriver(PropertyFile prop) throws Exception {
        capabilities = new DesiredCapabilities();
        String browserName;

        capabilities.setCapability("udid", DEVICE_NAME);

        //Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);
        System.out.println("Platform was initialized");

        //Setup type of application: mobile, webTests (or hybrid)
        if (AUT != null && SUT == null) {
            //Native
            System.out.println("AUT: " + AUT);
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability("appPackage", APP_PACKAGE);
            capabilities.setCapability("appActivity", APP_ACTIVITY);
            System.out.println("AUT was initialized");
        } else if (SUT != null && AUT == null) {
            //Web
            System.out.println("SUT: " + SUT);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            System.out.println("SUT was initialized");
        } else {
            throw new Exception("Unclear type of mobile app");
        }
        System.out.println(capabilities.toString());

        //Init driver for local Appium server with capabilities have been set
        if (driverSingle == null) {
            driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
            System.out.println("Driver was initialized");
        }

        //Set an object to handle timeouts
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(prop), 10);
            System.out.println("Wait was initialized");
        }
    }

    protected AppiumDriver driver(PropertyFile prop) throws Exception {
        if (driverSingle == null) {
            System.out.println("Driver in driver() method is null");
            prepareDriver(prop);
        }
        return driverSingle;
    }

    protected WebDriverWait driverWait() {
        return waitSingle;
    }
}