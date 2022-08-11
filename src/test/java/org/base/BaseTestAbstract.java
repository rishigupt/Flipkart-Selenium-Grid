package org.base;

import org.constants.DriverType;
import org.factory.abstractFactory.DriverManagerAbstract;
import org.factory.abstractFactory.DriverManagerFactoryAbstract;
import org.utils.CookieUtils;
import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.List;

public class BaseTestAbstract {

    private ThreadLocal<DriverManagerAbstract> driverManager = new ThreadLocal<>();
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriverManager(DriverManagerAbstract driverManager) {
        this.driverManager.set(driverManager);
    }

    protected DriverManagerAbstract getDriverManager() {
        return this.driverManager.get();
    }

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
        //browser = System.getProperty("browser", browser);
        if (browser == null) browser = "CHROME";
        setDriverManager(DriverManagerFactoryAbstract.getManager(DriverType.valueOf(browser)));
        setDriver(getDriverManager().getDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
        "Driver = " + getDriver());
    }

    @AfterMethod
    public void quitDriver(){
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "Driver = " + getDriver());
//        getDriver().quit();
        getDriverManager().getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

}
