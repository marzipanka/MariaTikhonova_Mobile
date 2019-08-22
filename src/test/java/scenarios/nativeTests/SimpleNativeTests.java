package scenarios.nativeTests;

import enums.PropertyFile;
import hooks.Hooks;
import io.appium.java_client.android.AndroidElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewContactPage;

import static org.testng.Assert.*;

/**
 * TO RUN TESTS YOU NEED TO HAVE AN ENVIRONMENT VARIABLE 'EPAM_TOKEN'
 * WITH YOUR ACCESS TOKEN VALUE IN IT
 * (MINSK NODE)
 *
 * Also, to run native tests you need to install 'ContactManager.apk' manually on the mobile device.
 */

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    private HomePage homePage;
    private NewContactPage newContactPage;

    protected SimpleNativeTests() throws Exception {
        super(PropertyFile.NATIVE);
        homePage = new HomePage(driver());
        newContactPage = new NewContactPage(driver());
    }

    @Test(groups = "native", description = "Just click on button 'Add contact'")
    public void simplestTest() throws Exception {

        //click Add Contact button
        clickElement(homePage.getAddContactButton());

        //check Target Account field is displayed
        checkElementIsDisplayed(newContactPage.getTargetAccount());

        //check Contact Name field is displayed
        checkElementIsDisplayed(newContactPage.getContactName());

        //check Contact Phone field is displayed
        checkElementIsDisplayed(newContactPage.getContactPhone());

        //check keyboard is displayed
        clickElement(newContactPage.getContactName());
        fillTextFieldWithText(newContactPage.getContactName(), "Bob");
        checkTextInTextFieldIsDisplayed(newContactPage.getContactName(), "Bob");

        System.out.println("Simplest Appium test done");
    }

    private void checkElementIsDisplayed(AndroidElement element) {
        assertTrue(element.isDisplayed());
    }

    private void clickElement(AndroidElement element) {
        element.click();
    }

    private void checkTextInTextFieldIsDisplayed(AndroidElement element, String text) {
        assertEquals(element.getText(), text);
    }

    private void fillTextFieldWithText(AndroidElement element, String text) {
        element.sendKeys(text);
    }
}
