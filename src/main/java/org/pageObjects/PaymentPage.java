package org.pageObjects;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.utils.PageHelper;

public class PaymentPage {
    public static final Logger log = LogManager.getLogger(PaymentPage.class.getName());
    public AppiumDriver driver;

    public PaymentPage(AppiumDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    //Locators
    @FindBy(id = "com.experitest.ExperiBank:id/makePaymentButton")
    private WebElement buttonMakePayment;
    @FindBy(css = "[text*='Your balance is:']")
    private WebElement labelCurrentBalance;
    @FindBy(id = "com.experitest.ExperiBank:id/phoneTextField")
    private WebElement txtBoxPhoneNumber;
    @FindBy(id = "com.experitest.ExperiBank:id/nameTextField")
    private WebElement txtBoxName;
    @FindBy(id = "com.experitest.ExperiBank:id/amount")
    private WebElement buttonAmount;
    @FindBy(id = "com.experitest.ExperiBank:id/countryTextField")
    private WebElement txtBoxCountry;
    @FindBy(id = "com.experitest.ExperiBank:id/sendPaymentButton")
    private WebElement buttonSendPayment;
    @FindBy(id = "android:id/button1")
    private WebElement buttonPaymentConfirmation;

    /**
     * This method will fetch the current account balance
     * @return balance
     */
    public long getCurrentBalance(){
        String text=labelCurrentBalance.getText();
        String bal=text.split(":")[1].replace("$","");
        double balance=Double.parseDouble(bal);
        return (long)balance;
    }

    /**
     * This method will make the payment
     * @param phoneNumber
     * @param name
     * @param amount
     * @param country
     * @throws Exception
     */
    public void makePayment(String phoneNumber,String name,long amount,String country) throws Exception {
        log.info("Making the payment");
        PageHelper.explicitWait(driver,buttonMakePayment,"ElementClickable");
        buttonMakePayment.click();
        PageHelper.explicitWait(driver,txtBoxPhoneNumber,"ElementToBeDisplayed");
        txtBoxPhoneNumber.sendKeys(phoneNumber);
        log.info("Entered phone number is "+phoneNumber);
        txtBoxName.sendKeys(name);
        log.info("Entered name is "+name);
        buttonAmount.click();
        txtBoxCountry.sendKeys(country);
        log.info("Selected Country name is "+country);
        buttonSendPayment.click();
        PageHelper.explicitWait(driver,buttonPaymentConfirmation,"ElementClickable");
        buttonPaymentConfirmation.click();
    }

    /**
     * This method will fetch the current account balance
     * @return balance
     */
    public void balanceCheck(long currentBalance,long sentAmount){
        String text=labelCurrentBalance.getText();
        String bal=text.split(":")[1].replace("$","");
        double balance=Double.parseDouble(bal);
        long savingsBal=currentBalance-sentAmount;
        Assert.assertEquals((long)balance,savingsBal,"Verification of the balance");
    }
}
