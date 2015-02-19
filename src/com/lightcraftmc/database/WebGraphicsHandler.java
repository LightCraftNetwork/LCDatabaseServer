package com.lightcraftmc.database;

import java.util.ArrayList;

import com.lightcraftmc.database.util.Tag;
import com.lightcraftmc.database.util.UtilBootstrap;
import com.lightcraftmc.database.util.UtilLoginPage;
import com.lightcraftmc.login.LoginManager;

public class WebGraphicsHandler {

    public static String handleResponse(String query, String response, String address) {
        if(query.equalsIgnoreCase("#login-page")){
            return handleLoginPage();
        }
        boolean didSucceed = !response.startsWith("FAILED: ");
        response = response.replaceFirst("FAILED: ", "");
        response = response.replaceFirst("SUCCESS: ", "");
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(UtilBootstrap.generateTitle("LCDatabaseServer Results"));
        lines.add(Tag.open("body"));
        if(!LoginManager.getInstance().isLoggedIn(address)){
            lines.addAll(UtilBootstrap.createError("You must sign in to access this page. The login page can be found <a href=\"/\">here</a>.</b></h5>"));
        }
        // TODO format
        lines.addAll(UtilBootstrap.container("Results", "Query: " + query, response));
        lines.addAll(UtilBootstrap.actionForm("publicKey"));
        lines.add(Tag.close("body"));
        lines.add(Tag.close("html"));
        String builder = "";
        for (String line : lines) {
            builder = builder + line + "\n";
        }
        return builder;
    }
    
    public static String handleLoginPage(){
        String builder = "";
        for (String line : UtilLoginPage.getLoginPage()) {
            builder = builder + line + "\n";
        }
        return builder;
    }

}
