package org.selenium.pages;

import org.selenium.objects.LoginData;
import org.selenium.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    By closeLoginPopup_XPATH = By.xpath("//button[@class='_2KpZ6l _2doB4z']");
    private By loginPopup_CSS = By.cssSelector("div._2MlkI1");
    private By loginId_XPATH = By.xpath("//div[@class='IiD88i _351hSN']/input[@type='text']");
    private By loginPW_XPATH = By.xpath("//input[@type='password']");
    private By loginBtn_XPATH = By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']");

    By viewAllBtn_XPATH = By.cssSelector("a._2KpZ6l._3dESVI");
    By openLoginPopupBtn_XPATH = By.xpath("//a[@class='_1_3w1N']");
    By searchForProducts_XPATH = By.xpath("//input[@type='text']");
    By searchButton = By.xpath("//button[@type='submit']");
    By sortByLowestPrice = By.xpath("//div[text()='Price -- Low to High']");

    private By myAccount_XPATH = By.xpath("//div[text()='My Account']");
    private By myProfile_XPATH = By.xpath("//div[text()='My Profile']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load(){
        load("/");
        //wait.until(ExpectedConditions.titleContains("Online Shopping Site"));
        return this;
    }

    @Step
    public HomePage closeLoginPopup() {
        driver.findElement(closeLoginPopup_XPATH).click();
        return this;
    }

    public boolean isLoginPopupDisplayed() {
        driver.findElement(loginPopup_CSS).isDisplayed();
        return true;
    }

    public HomePage enterUserName(String loginID) {
        driver.findElement(loginId_XPATH).sendKeys(loginID);
        return this;
    }

    public HomePage enterPassword(String loginPW) {
        driver.findElement(loginPW_XPATH).sendKeys(loginPW);
        return this;
    }

    public HomePage clickLoginBtn() {
        driver.findElement(loginBtn_XPATH).click();
        return this;
    }


    public HomePage login(String loginID, String loginPW) throws InterruptedException {
        Thread.sleep(2000);
        enterPassword(loginPW);
        enterUserName(loginID);
        clickLoginBtn();
        return this;
    }

    public HomePage setLoginDetails(LoginData loginData) {
        return enterUserName(loginData.getUsername()).enterPassword(loginData.getPassword());
    }

    @Step
    public HomePage searchProduct(String productName) {
        driver.findElement(searchForProducts_XPATH).sendKeys(productName);
        return this;
    }

    @Step
    public HomePage clickSearchButton() {
        driver.findElement(searchButton).click();
        return this;
    }

    @Step
    public HomePage sortByLowest() {
        wait.until(ExpectedConditions.elementToBeClickable(sortByLowestPrice)).click();
        return this;
    }

    public HomePage mouseHoverMyAccount (){
        actions.moveToElement(waitForElementToBeVisible(myAccount_XPATH)).build().perform();
        return this;
    }

    public AccountPage clickMyProfile (){
        driver.findElement(myProfile_XPATH).click();
        return new AccountPage(driver);
    }




}
