package org.rishi.automation.browserfactory;

import org.rishi.automation.constants.DriverType;

import java.net.MalformedURLException;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType driverType) throws MalformedURLException {
        switch (driverType){
            case CHROME:
                return new ChromeDriverManager();
            case EDGE:
                return new EdgeDriverManager();
            default:
                throw new IllegalStateException("Invalid driver: " + driverType);
        }
    }
}
