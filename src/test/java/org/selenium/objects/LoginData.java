package org.selenium.objects;

public class LoginData {

    private String username;
    private String password;
    private String email;

    //Default Constructor
    public LoginData(){

    }

    //Parameterized Constructor
    public LoginData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public LoginData setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginData setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public LoginData setEmail(String email) {
        this.email = email;
        return this;
    }



}
