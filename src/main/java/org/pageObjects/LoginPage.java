package org.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utils.PageHelper;

public class LoginPage {

    public static final Logger log = LogManager.getLogger(LoginPage.class.getName());
    public AppiumDriver driver;

    public LoginPage(AppiumDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }



    @FindBy(id = "android:id/content")
    private WebElement warningFrame;
    @FindBy(id = "//android:id/button1")
    private WebElement versionWarning;
    @FindBy(id = "com.experitest.ExperiBank:id/usernameTextField")
    private WebElement txtBoxUserName;
    @FindBy(id = "com.experitest.ExperiBank:id/passwordTextField")
    private WebElement txtBoxPassword;
    @FindBy(id = "com.experitest.ExperiBank:id/loginButton")
    private WebElement buttonLogin;
    @FindBy(id = "com.experitest.ExperiBank:id/makePaymentButton")
    private WebElement buttonMakePayment;

    /**
     * login to EriBank
     * @param username
     * @param password
     */
    public void login(String username,String password) throws Exception {
         log.info("Logging in to EriBank");
         PageHelper.explicitWait(driver,txtBoxUserName,"ElementToBeDisplayed");
         txtBoxUserName.sendKeys(username);
         txtBoxPassword.sendKeys(password);
         log.info("Entered username and password");
         buttonLogin.click();
    }
    public WebElement getMakePayment() throws Exception {
        PageHelper.explicitWait(driver,buttonMakePayment,"ElementToBeDisplayed");
        return buttonMakePayment;
    }

}
