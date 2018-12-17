package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSet {

    public static void main(String[] args) {
        Proxy seleniumProxy = null;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Selenium\\Browsers\\GoogleChromePortable\\GoogleChromePortable.exe");


        InternetExplorerOptions ieOptions = new InternetExplorerOptions()
                .destructivelyEnsureCleanSession();
        capabilities.setCapability("se:ieOptions", ieOptions);

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile ffprofile = profile.getProfile("SELENIUM");
        WebDriver driver2 = new FirefoxDriver((Capabilities) ffprofile);

        WebDriver driver = null;
        By by = By.xpath("");
        driver.switchTo().alert().accept();

    }
}
