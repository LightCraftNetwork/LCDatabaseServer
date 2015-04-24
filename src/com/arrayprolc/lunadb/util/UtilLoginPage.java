package com.arrayprolc.lunadb.util;

import java.util.ArrayList;

public class UtilLoginPage {

    private static ArrayList<String> loginPage;

    // TODO make this only called once.
    public static ArrayList<String> getLoginPage() {
        if (loginPage != null) {
            return loginPage;
        }
        loginPage = new ArrayList<String>();
        loginPage.clear();
        loginPage.addAll(UtilBootstrap.generateTitle("LunaDB login"));
        loginPage.add(Tag.open("body"));
        loginPage.addAll(UtilBootstrap.createError("You must sign in to access this page."));
        loginPage.addAll(UtilBootstrap.container("Sign in", "Sign in to your account", "You must enter an access token given by your administrator.\nYou can also use 'publicKey' (without quotes) to access public commands."));
        loginPage.addAll(UtilBootstrap.loginForm());
        
        loginPage.add(Tag.close("body"));
        loginPage.add(Tag.close("html"));

        return loginPage;
    }

}
