package org.selenium.factory;

import org.selenium.constants.DriverType;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType driverType){
        switch (driverType){
            case CHROME:
                return new ChromeDriverManager();
            case EDGE:
                return new EdgeDriverManager();
            default:
                throw new IllegalStateException("Invalid dirver: " + driverType);
        }
    }
}
