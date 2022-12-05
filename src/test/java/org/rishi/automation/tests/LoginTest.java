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
import java.net.MalformedURLException;

@Epic("Flipkart Epic")
@Feature("Search Function Feature")
public class LoginTest extends BaseTest {

    public LoginTest() throws MalformedURLException {
        //super();
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




}