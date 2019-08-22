package scenarios.webTests;

import enums.PropertyFile;
import hooks.Hooks;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.testng.Assert.assertEquals;

@Test(groups = "web")
public class SimpleWebTests extends Hooks {

    protected SimpleWebTests() throws IOException {
        super(PropertyFile.WEB);
    }

    @Test(groups = "web", description = "Open website")
    public void webTest() throws Exception {
        driver().get(SUT);
        driverWait().until(ExpectedConditions.urlToBe(SUT + "/"));

        //check site is opened
        assertEquals(driver().getTitle(), "Internet Assigned Numbers Authority");

        //check status code
        checkStatusCode(SUT, 200);

        System.out.println("Site opening done");
    }

    private void checkStatusCode(String site, int expectedCode) throws Exception {
        URL url = new URL(site);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        int statusCode = http.getResponseCode();
        assertEquals(statusCode, expectedCode);
    }
}
