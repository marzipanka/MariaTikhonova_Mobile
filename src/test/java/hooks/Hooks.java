package hooks;

import enums.PropertyFile;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import setup.Driver;

import java.io.IOException;

public class Hooks extends Driver {

    private PropertyFile prop;
    /**
     * Required variables will be initialised by inherited constructor
     * @throws Exception
     */
    public Hooks(PropertyFile prop) throws IOException {
        super(prop);
        this.prop = prop;
    }

    @BeforeSuite(description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        prepareDriver(prop);
        System.out.println("Driver prepared");
    }

    @AfterSuite(description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }

    public void prepareDriver(PropertyFile prop) throws Exception {
            super.prepareDriver(prop);
    }

    public AppiumDriver driver() throws Exception {
        return super.driver(prop);
    }
}
