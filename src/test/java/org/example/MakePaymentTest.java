package org.example;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pageObjects.LoginPage;
import org.pageObjects.PaymentPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.utils.Constants;
import org.utils.DriverConfig;
import org.utils.ExcelUtility;
import org.utils.Utils;

import java.io.IOException;

public class MakePaymentTest extends DriverConfig {
    public static final Logger log = LogManager.getLogger(MakePaymentTest.class.getName());
    public AppiumDriver driver;
    ExtentTest test;
    @BeforeTest
    public void login(){
        try {
            test=extentReports.createTest("EriBank Login Test","Login to the application");
            test.log(Status.INFO,"Test Started");
            driver=getDriver(Utils.getProperty("device"));
            LoginPage loginPage=new LoginPage(driver);
            loginPage.login(Utils.getProperty("username"),Utils.getProperty("password"));
            Assert.assertTrue(loginPage.getMakePayment().isDisplayed(),"Login is Unsuccessful");
            test.log(Status.PASS,"Login is Successful");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test(dataProvider = "paymentData")
    public void payment(String phoneNumber,String name,long amount,String country){
        test=extentReports.createTest("EriBank Payment Test","This Test will help in making the payment");
        test.log(Status.INFO,"Test Started");
        PaymentPage paymentPage=new PaymentPage(driver);
        long balance=paymentPage.getCurrentBalance();
        log.info("Current Balance is "+balance);
        test.log(Status.INFO,"Current Balance is "+balance);
        try {
            paymentPage.makePayment(phoneNumber,name,amount,country);
            test.log(Status.INFO,"transferred amount is "+amount);
            paymentPage.balanceCheck(balance,amount);
            test.log(Status.PASS,"Balance verification is Successful");
        } catch (Exception e) {
            e.printStackTrace();
        }
        test.log(Status.PASS,"Payment is Successful");
    }

    @DataProvider(name="paymentData")
    public Object[][] getData() throws IOException {
        String base=System.getProperty("user.dir");
        String path=base.concat(Constants.TEST_DATA_PATH).concat("TestData.xlsx");
        String sheetName="Sheet1";
        ExcelUtility utility=new ExcelUtility(path);
        int noOfRows=utility.getRowCount(sheetName);
        int noOfColumns=utility.getCellCount(sheetName,0);
        Object[][] paymentData=new Object[noOfRows][noOfColumns];
        for(int i=1;i<=noOfRows;i++){
            for (int j=0;j<noOfColumns;j++){
                paymentData[i-1][j]=utility.getCellData(sheetName,i,j);
            }
        }
        return paymentData;
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
