package org.rishi.automation.browserfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class EdgeDriverManager implements DriverManager {

    @Override
    public WebDriver createDriver(URL hubUrl) {
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriver driver = new RemoteWebDriver(hubUrl,  edgeOptions);
        driver.manage().window().maximize();
        return driver;
    }
}
