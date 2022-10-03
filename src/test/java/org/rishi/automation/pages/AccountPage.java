package org.rishi.automation.pages;

import org.openqa.selenium.By;
import org.rishi.automation.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AccountPage extends BasePage {

    private By logoutBtn = By.xpath("//span[text()='Logout']");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountPage isLoaded(){
        wait.until(ExpectedConditions.urlContains("/account"));
        return this;
    }

    public HomePage clickLogoutBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutBtn)).click();
        return new HomePage(driver);
    }

}
