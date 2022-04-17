package org.utils;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageHelper {
    public static final Logger log = LogManager.getLogger(PageHelper.class.getName());
    /**
     * This explicit wait Utility wait for selected type of operations
     *
     * @param driver
     * @param element
     * @param type
     */
    public static void explicitWait(WebDriver driver, WebElement element, String type) throws Exception {
        log.info("Implementing Explicit Wait for WebElements based on waiting type");
        switch (type) {
            case "ElementClickable":
                new WebDriverWait(driver, Duration.ofSeconds(60))
                        .until(ExpectedConditions.elementToBeClickable(element));
                break;
            case "ElementToBeDisplayed":
                new WebDriverWait(driver, Duration.ofSeconds(60))
                        .until(ExpectedConditions.visibilityOf(element));
                break;
            case "ElementToBeSelected":
                new WebDriverWait(driver, Duration.ofSeconds(60))
                        .until(ExpectedConditions.elementToBeSelected(element));
                break;
            default:
                log.error("Please specify the type");
        }
    }

    /**
     * This method will switch to a specific frame
     * @param driver
     * @param element
     */
    public static void switchToFrame(AppiumDriver driver, WebElement element){
        try{
            driver.switchTo().frame(element);
        }catch (NoSuchFrameException nsf){
            log.error("frame is not found");
        }
    }
    /**
     * This method will switch to a specific frame
     * @param driver
     */
    public static void switchBackFromFrame(AppiumDriver driver){
        try{
            driver.switchTo().parentFrame();
        }catch (NoSuchFrameException nsf){
            log.error("frame is not found");
        }
    }
}
