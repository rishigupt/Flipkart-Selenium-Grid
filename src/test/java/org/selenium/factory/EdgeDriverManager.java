package org.selenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager implements DriverManager {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.edgedriver().cachePath("Drivers").setup();
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
