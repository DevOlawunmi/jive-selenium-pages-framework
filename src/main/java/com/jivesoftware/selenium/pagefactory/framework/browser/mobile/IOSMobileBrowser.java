package com.jivesoftware.selenium.pagefactory.framework.browser.mobile;

import com.jivesoftware.selenium.pagefactory.framework.actions.IOSSeleniumActions;
import com.jivesoftware.selenium.pagefactory.framework.config.TimeoutsConfig;
import com.jivesoftware.selenium.pagefactory.framework.exception.JiveWebDriverException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;


/**
 * Added by Shiran.Dadon
 * Known bug of Apple from Xcode 5 and iOS 7.1 Simulator - swipe is not working on simulator.
 * As a workaround, using scrollTo in JavaScript.
 * As in real devices regular swipe works but not scrollTo, using the regular command as well
 */

public class IOSMobileBrowser extends MobileBrowser {


    public IOSMobileBrowser(String baseTestUrl,
                            String browserName,
                            String platformName,
                            String platformVersion,
                            String deviceName,
                            String newCommandTimeout,
                            String automationName,
                            String version,
                            String autoLaunch,
                            String app,
                            TimeoutsConfig timeouts) throws JiveWebDriverException {
        super(baseTestUrl, timeouts, browserName, platformName, platformVersion, deviceName,
                newCommandTimeout, automationName, version, autoLaunch, app);
    }

    @Override
    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.BROWSER_NAME, browserName);
        desiredCapabilities.setCapability("platformName", platformName);
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("newCommandTimeout", newCommandTimeout);
        desiredCapabilities.setCapability("automationName", automationName);
        desiredCapabilities.setCapability("version", version);
        desiredCapabilities.setCapability("autoLaunch", autoLaunch);
        desiredCapabilities.setCapability("app", app);
        desiredCapabilities.setCapability("fullReset", "false");
        desiredCapabilities.setCapability("rotatable", "true");
        return desiredCapabilities;
    }

    @Override
    public IOSSeleniumActions getActions() {
        return new IOSSeleniumActions(this);
    }
    /**
     * Swipe from the right to left for a second
     */
    public void swipeLeft() {
        super.swipeLeft();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "left");
        webDriver.executeScript("mobile: scroll", scrollObject);
    }

    /**
     * Swipe from the left to right for a second
     */
    public void swipeRight() {
        super.swipeRight();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "right");
        webDriver.executeScript("mobile: scroll", scrollObject);
    }

    /**
     * Swipe from the top to buttom for a second
     */
    public void dragDown() {
        int midScreen = getScreenWidth() / 2;
        webDriver.swipe(midScreen, 140, midScreen, getScreenHeight() - 140, 1500);
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "up");
        webDriver.executeScript("mobile: scroll", scrollObject);
    }

    /**
     * Swipe from the down to up for a second
     */
    public void dragUp() {
        int midScreen = getScreenWidth() / 2;
        webDriver.swipe(midScreen, getScreenHeight() - 140, midScreen, 140, 1500);
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        webDriver.executeScript("mobile: scroll", scrollObject);
    }

    /**
     *
     * @param startX - 0 is the left side of the smart-phone
     * @param endX
     * @param startY - 0 is the upper side of the smart-phone
     * @param endY
     * @param duration - in milliseconds
     * Will function only with real device
     */
    public void swipe(int startX, int endX, int startY, int endY, int duration) {
        webDriver.swipe(startX, startY, endX, endY, duration);
    }

    public void scrollToTop() {
        getWebDriver().findElementByClassName("UIAStatusBar").click();
    }

    public void openNotifications() {
        int midScreenWidth = getScreenWidth() / 2 ;
        webDriver.swipe(midScreenWidth, 0, midScreenWidth, getScreenHeight(), 1000);
        webDriver.quit();
    }
}
