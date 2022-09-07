package org.selenium.tests;
import org.selenium.objects.LoginData;
import org.selenium.objects.ProductData;
import org.selenium.pages.AccountPage;
import org.selenium.pages.HomePage;
import org.selenium.utils.JacksonUtils;
import org.selenium.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Flipkart Epic")
@Feature("Search Function Feature")
public class FirstTest extends BaseTest {

    public FirstTest() {
    }

    @Test
    public void loginAndLogout() throws IOException {
        //calling setters method
        /*LoginData loginData = new LoginData().
                setUsername("UserName").
                setPassword("Password");*/

        //providing data using parameterized constructor
        /*LoginData loginData = new LoginData("UserName" , "Password");*/

        //Parsing the json object and converting it to java object
        LoginData loginData = JacksonUtils.deserializeJson("loginDetails.json", LoginData.class);
        HomePage homePage = new HomePage(getDriver()).load();
        //using the java object loginData via HomePage.setLoginDetails method
        AccountPage accountPage = homePage.setLoginDetails(loginData).
                clickLoginBtn().
                mouseHoverMyAccount().
                clickMyProfile();
        accountPage.clickLogoutBtn();
    }

    @Story("add a product story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @TmsLink("test-1")
    @Description("this is the description")
    @Test(description = "should be able to add product")
    public void addProduct() throws IOException {
        ProductData productData = new ProductData(1215);

        HomePage homePage = new HomePage(getDriver()).load();
        homePage.closeLoginPopup();
        homePage.searchProduct(productData.getName())
                .clickSearchButton()
                .sortByLowest();
    }



}