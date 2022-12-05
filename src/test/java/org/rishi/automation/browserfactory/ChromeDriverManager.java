package org.rishi.automation.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class ChromeDriverManager implements DriverManager{

    @Override
    public WebDriver createDriver(URL hubUrl) {
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setAcceptInsecureCerts(true);
        WebDriver driver = new RemoteWebDriver(hubUrl, chromeOptions);
        driver.manage().window().maximize();
        return driver;
    }
}
