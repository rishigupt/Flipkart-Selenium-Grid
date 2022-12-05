package org.rishi.automation.tests;


import org.rishi.automation.objects.ProductData;
import org.rishi.automation.pages.HomePage;
import org.rishi.automation.base.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;

public class SearchTest extends BaseTest{

    public SearchTest() throws MalformedURLException {
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
        System.out.println("first test");
    }

    @Test(enabled = true)
    public void gridCases() {
            getDriver().get("https://google.com");
            System.out.println(getDriver().getTitle());
    }
}
