package org.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    protected void startDriver() {
        WebDriverManager.edgedriver().cachePath("Drivers").setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
}
