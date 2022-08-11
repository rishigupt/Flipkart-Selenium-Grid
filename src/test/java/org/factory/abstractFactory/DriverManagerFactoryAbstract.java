package org.factory.abstractFactory;

import org.constants.DriverType;

public class DriverManagerFactoryAbstract {
    public static DriverManagerAbstract getManager(DriverType driverType){
        switch (driverType){
            case CHROME:
                return new ChromeDriverManagerAbstract();
            case EDGE:
                return new EdgeDriverManagerAbstract();
            default:
                throw new IllegalStateException("Invalid dirver: " + driverType);
        }
    }
}
