package org.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.reports.ExtentReportUtil;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DriverConfig extends ExtentReportUtil {

    public AppiumDriver driver;
    /**
     * This method will return the Android driver instance
     * @return
     */
    public AppiumDriver getDriver(String platform) throws IOException {
        switch (platform.toUpperCase()){
            case "ANDROID":
                String base=System.getProperty("user.dir");
                File parent=new File(base.concat(Constants.CAPS_APP_PATH));
                File app=new File(parent, Constants.CAPS_APP_NAME);
                DesiredCapabilities caps=new DesiredCapabilities();
                caps.setCapability(MobileCapabilityType.DEVICE_NAME,Utils.getProperty("deviceName"));
                caps.setCapability(MobileCapabilityType.APP,app.getAbsolutePath());
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,Constants.CAPS_AUTOMATION_NAME);
                driver=new AndroidDriver(new URL(Utils.getProperty("URL")),caps);
                break;
            case "IOS":
                //to do
                break;
        }

        return driver;
    }
}
