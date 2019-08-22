package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class NewContactPage {

    protected AppiumDriver driver;
    private static final String app_package_name = "com.example.android.contactmanager:id/";

    @AndroidFindBy(id = app_package_name + "accountSpinner")
    private AndroidElement targetAccount;

    @AndroidFindBy(id = app_package_name + "contactNameEditText")
    private AndroidElement contactName;

    @AndroidFindBy(id = app_package_name + "contactPhoneEditText")
    private AndroidElement contactPhone;

    public NewContactPage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}
