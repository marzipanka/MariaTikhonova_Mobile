package scenarios.nativeTests;

import enums.PropertyFile;
import hooks.Hooks;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

@Test(groups = "native")
public class SimpleNativeTests extends Hooks {

    protected SimpleNativeTests() throws IOException {
        super(PropertyFile.NATIVE);
    }

    @Test(groups = "native", description = "Just click on button 'Add contact'")
    public void simplestTest() throws Exception {

        String app_package_name = "com.example.android.contactmanager:id/";

        //click of Add Contact button
        By add_btn = By.id(app_package_name + "addContactButton");
        driver().findElement(add_btn).click();
        System.out.println("Add Contact button clicked");

        //check Target Account field is displayed
        checkContactManagerElementIsDisplayed("accountSpinner");

        //check Contact Name field is displayed
        checkContactManagerElementIsDisplayed("contactNameEditText");

        //check Contact Phone field is displayed
        checkContactManagerElementIsDisplayed("contactPhoneEditText");

        System.out.println("Simplest Appium test done");
    }

    private void checkContactManagerElementIsDisplayed(String id) throws Exception {
        String app_package_name = "com.example.android.contactmanager:id/";
        By element = By.id(app_package_name + id);
        assertTrue(driver().findElement(element).isDisplayed());
        System.out.println(id + " checked");
    }
}
