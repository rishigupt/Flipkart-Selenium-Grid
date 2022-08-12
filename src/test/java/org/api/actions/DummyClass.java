package org.api.actions;
import org.objects.LoginData;
import org.utils.FakerUtils;

public class DummyClass {

    public static void main(String[] args) {
        String username =   "demouser" + new FakerUtils().generateRandomNumber();
        LoginData user = new LoginData().
                             setUsername(username).
                             setPassword("demopwd").
                             setEmail(username + "@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES: " + signUpApi.getCookies());


        CartApi cartApi =  new CartApi(signUpApi.getCookies());
        cartApi.addToCart(1215, 1);
        System.out.println("CART COOKIES: " + cartApi.getCookies());

    }

    public void dummy(){
        System.out.println("dummy");
    }

}
