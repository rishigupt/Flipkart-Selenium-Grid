package org.pages;

import org.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AccountPage extends BasePage {

    //private By logoutBtn_XPATH = By.xpath("//span[text()='Logout']");
   @FindBy(xpath = "//span[text()='Logout']") @CacheLookup WebElement logoutBtn_XPATH;

    public AccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded(){
       return wait.until(ExpectedConditions.urlContains("/account"));
    }

    public HomePage clickLogoutBtn () {
        //wait.until(ExpectedConditions.elementToBeClickable(logoutBtn_XPATH)).click();
        waitForElementToBeClickable(logoutBtn_XPATH).click(); //via PageFactory
        return new HomePage(driver);
    }

}
