package org.rishi.automation.browserfactory;

import org.openqa.selenium.WebDriver;

import java.net.URL;


public interface DriverManager {


    WebDriver createDriver(URL hubUrl);


}
