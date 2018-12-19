package appium;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class AppiumSample {
    public static void main(String[] args) {
        io.appium.java_client.AppiumDriver<MobileElement> driver = null;
        driver.manage().timeouts().implicitlyWait(1213123L, TimeUnit.SECONDS);

        Function<String, By> optStarGradeFunction = strStar -> By.xpath("//input[@id='" + strStar + "'");
        By optStarGrade = optStarGradeFunction.apply("XPath");
    }


    private static By findByXPath(String xpath) {
        return By.xpath("//input[@id='" + xpath + "'");
    }

}