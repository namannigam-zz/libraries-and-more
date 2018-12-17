package appium;

import io.appium.java_client.MobileElement;

import java.util.concurrent.TimeUnit;

public class AppiumSample {
    public static void main(String[] args) {
        io.appium.java_client.AppiumDriver<MobileElement> driver = null;
        driver.manage().timeouts().implicitlyWait(1213123L, TimeUnit.SECONDS);
    }

}