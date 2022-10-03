package org.rishi.automation.tests;
import org.rishi.automation.objects.ProductData;
import org.rishi.automation.pages.AccountPage;
import org.rishi.automation.objects.LoginData;
import org.rishi.automation.pages.HomePage;
import org.rishi.automation.utils.JacksonUtils;
import org.rishi.automation.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Flipkart Epic")
@Feature("Search Function Feature")
public class LoginTest extends BaseTest {

    public LoginTest() {
    }

    @Test
    public void loginAndLogout() throws IOException {

        LoginData loginData = JacksonUtils.deserializeJson("loginDetails.json", LoginData.class);
        HomePage homePage = new HomePage(getDriver()).load();
        AccountPage accountPage = homePage.setLoginDetails(loginData)
                .clickLoginBtn()
                .mouseHoverMyAccount()
                .clickMyProfile();
        accountPage
                .isLoaded()
                .clickLogoutBtn();
    }

/*    @Test(enabled = false)
    public void loginAndLogoutWithSetters() {

        LoginData loginData = new LoginData().
                setUsername("lonor10769@dnitem.com").
                setPassword("jaiho");
        HomePage homePage = new HomePage(getDriver()).load();
        AccountPage accountPage = homePage.setLoginDetails(loginData)
                .clickLoginBtn()
                .mouseHoverMyAccount()
                .clickMyProfile();
        accountPage
                .isLoaded()
                .clickLogoutBtn();
    }*/

/*    @Test
    public void loginAndLogoutWithConstructor() {

        LoginData loginData = new LoginData("lonor10769@dnitem.com" , "jaiho");
        HomePage homePage = new HomePage(getDriver()).load();
        AccountPage accountPage = homePage.setLoginDetails(loginData)
                .clickLoginBtn()
                .mouseHoverMyAccount()
                .clickMyProfile();
        accountPage
                .isLoaded()
                .clickLogoutBtn();
    }*/



}