package scenarios.webTests;

import enums.PropertyFile;
import hooks.Hooks;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    protected  SimpleWebTests() throws IOException {
        super(PropertyFile.WEB);
    }

    @Test(groups = "web", description = "Open website")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));

        //check site is opened
        assertEquals(driver().getTitle(), "Internet Assigned Numbers Authority");

        System.out.println("Site opening done");
    }
}
