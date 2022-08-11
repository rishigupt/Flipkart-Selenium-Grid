package org.factory;

import org.constants.DriverType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;


public class DriverManagerOriginal {

    public WebDriver initializeDriver(String browser) {
        WebDriver driver;
        browser = System.getProperty("browser", browser);
        switch (DriverType.valueOf(browser)) {
            case CHROME:
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
                break;
            case EDGE:
                WebDriverManager.edgedriver().cachePath("Drivers").setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalStateException("Invalid browser name: " + browser);
        }


      /*  switch (browser) {
            case "Chrome" -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
            }
            case "Edge" -> {
                WebDriverManager.edgedriver().cachePath("Drivers").setup();
                driver = new EdgeDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name: " + browser);
        }*/

        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
