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

public class SearchTest extends BaseTest{

    public SearchTest() {
    }


    @Story("add a product story")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123")
    @TmsLink("test-1")
    @Description("this is the description")
    @Test(description = "should be able to search product", enabled = true)
    public void searchProduct() throws IOException {

        ProductData productData = new ProductData(1215);
        HomePage homePage = new HomePage(getDriver()).load();
        homePage.closeLoginPopup();
        homePage.searchProduct(productData.getName())
                .clickSearchButton()
                .sortByLowest();
    }
}
