package org.tests;

import org.api.actions.CartApi;
import org.api.actions.SignUpApi;
import org.base.BaseTestOriginal;
import org.objects.LoginData;
import org.objects.ProductData;
import org.utils.FakerUtils;

import java.io.IOException;

public class LoginTest extends BaseTestOriginal {

   // @Test
    public void loginDuringCheckout() throws IOException {
        String username =   "demouser" + new FakerUtils().generateRandomNumber();
        LoginData user = new LoginData().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        ProductData productData = new ProductData(1215);
        cartApi.addToCart(productData.getId(), 1);
        injectCookiesToBrowser(cartApi.getCookies());
    }
}
