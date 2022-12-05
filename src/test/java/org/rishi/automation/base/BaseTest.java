package org.rishi.automation.base;

import org.apache.commons.io.FileUtils;
import org.rishi.automation.browserfactory.DriverManagerFactory;
import org.rishi.automation.constants.DriverType;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.rishi.automation.utils.TerminalUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class BaseTest  {
    
    private URL hubUrl = new URL("http://localhost:4444/wd/hub");
    //private String host;


    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public BaseTest() throws MalformedURLException {
    }

    /* public BaseTest() throws MalformedURLException {

       *//* host = "192.168.29.42";

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }
        hubUrl = new URL("http://" + host + ":4444");*//*

        hubUrl = new URL("https://localhost:4444/wd/hub");

    }*/



    private void setDriver(WebDriver driver) {
        this.driver.set(driver);
    }

    protected WebDriver getDriver() {
        return this.driver.get();
    }

    @BeforeSuite
    public void startContainer() throws InterruptedException {
        TerminalUtils.runTerminalCommand("docker-compose up", "Node has been added");
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) throws MalformedURLException {
        if (browser == null) browser = "EDGE";
        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver(hubUrl));
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

    @AfterSuite
    public void stopContainer(){
        TerminalUtils.runTerminalCommand("docker-compose down", "Network flipkart_grid-docker_default");
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

}
