package hooks;

import enums.PropertyFile;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import setup.Driver;

import java.io.IOException;


public class Hooks extends Driver {

    private PropertyFile prop;

    /**
     * Required variables will be initialised by inherited constructor
     *
     * @throws Exception
     */
    public Hooks(PropertyFile prop) throws IOException {
        super(prop);
        this.prop = prop;
    }

    @BeforeGroups(groups = "native", description = "Prepare driver to run test(s)")
    public void setUpWeb() throws Exception {
        prop = PropertyFile.NATIVE;
        prepareDriver(prop);
        System.out.println("Native driver prepared");
    }

    @BeforeGroups(groups = "web", description = "Prepare driver to run test(s)")
    public void setUpNative() throws Exception {
        prop = PropertyFile.WEB;
        prepareDriver(prop);
        System.out.println("Web driver prepared");
    }

    @AfterGroups(groups = {"native", "web"}, description = "Close driver on all tests completion")
    public void tearDownNative() throws Exception {
        driver().closeApp();
        driverTearDown();
        System.out.println("Driver closed");
    }

    public void prepareDriver(PropertyFile prop) throws Exception {
        super.prepareDriver(prop);
    }

    public AppiumDriver driver() throws Exception {
        return super.driver(prop);
    }
}
