package org.tests;
import org.pages.AccountPage;
import org.base.BaseTest;
import org.objects.LoginData;
import org.objects.ProductData;
import org.pages.HomePage;
import org.utils.JacksonUtils;
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
        /*LoginDetails loginDetails = new LoginDetails().
                setUserName("6351991983").
                setPassword("oct-2019");*/

        //providing date using parameterized constructor
        //LoginDetails loginDetails = new LoginDetails("6351991983" , "oct-2019");

        //Parsing the json object and converting it to java object
        LoginData loginData = JacksonUtils.deserializeJson("loginDetails.json", LoginData.class);
        HomePage homePage = new HomePage(getDriver()).load();
        //using the java object loginData via setLoginDetails method
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