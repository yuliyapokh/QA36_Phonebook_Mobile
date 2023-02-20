package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.Auth;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    //     xpath = "//*[@class='android.widget.EditText']"
    // xpath = "//*[@text='Type email']"   !!!!!!!!  xpath = "//*[text()='Type email']"
    // xpath = "//*[@resource-id ='com.sheygam.contactapp:id/inputEmail']"
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;
    @FindBy(xpath = "//*[@resource-id ='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPassword")
    MobileElement passwordEditText;
    @FindBy(xpath = "//*[@text='LOGIN']")
    MobileElement loginButton;
    @FindBy (id="com.sheygam.contactapp:id/regBtn")
    MobileElement registrationButton;

    public ContactListScreen submitRegistration(){
        registrationButton.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen fillLoginRegistrationForm(Auth auth){

        should(emailEditText,15);
        type(emailEditText, auth.getEmail());
        type(passwordEditText, auth.getPassword());

        return this;
    }

    public AuthenticationScreen fillEmail(String  email){
        should(emailEditText,15);
        type(emailEditText,email);
        return this ;
    }
    public AuthenticationScreen fillPassword(String password){
        type(passwordEditText,password);
        return this;
    }
    public ContactListScreen submitLogin(){
        driver.hideKeyboard();
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public AuthenticationScreen submitLoginNegative(){
        driver.hideKeyboard();
        loginButton.click();
        return this;
    }
    public AuthenticationScreen isErrorMessageContainsText(String text){
        Alert alert = new WebDriverWait(driver,10)
                .until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains(text));
        alert.accept();

        return this;
    }
}