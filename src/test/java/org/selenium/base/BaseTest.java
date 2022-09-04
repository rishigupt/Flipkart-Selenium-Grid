package org.selenium.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.selenium.constants.DriverType;
import org.selenium.factory.DriverManagerFactory;
import org.selenium.utils.CookieUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class BaseTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser){
//        browser = System.getProperty("browser", browser);
        if (browser == null) browser = "CHROME";
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
        "Driver = " + getDriver());
    }

    @Parameters("browser")
    @AfterMethod
    public void quitDriver(@Optional String browser, ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId() + ", " +
                "Driver = " + getDriver());
        if (result.getStatus() == ITestResult.FAILURE){
            Date date = new Date();
            File destFile = new File("snapshot" + File.separator + browser + File.separator +
                    result.getTestClass().getRealClass().getSimpleName() + "_" +
                    result.getMethod().getMethodName() + "_" +
                    date.toString().replace(":", "_").replace(" ","_") + ".png");

            takeScreenshot(destFile);
            //takeScreenshotUsingAshot(destFile);
        }
        getDriver().quit();
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    private void takeScreenshotUsingAshot(File destFile){
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(getDriver());
        try {
            ImageIO.write(screenshot.getImage(), "PNG", destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for (Cookie cookie: seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

}
