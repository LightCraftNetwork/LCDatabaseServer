package com.arrayprolc.lunadb;

import java.util.ArrayList;

import com.arrayprolc.lunadb.login.LoginManager;
import com.arrayprolc.lunadb.util.Tag;
import com.arrayprolc.lunadb.util.UtilBootstrap;
import com.arrayprolc.lunadb.util.UtilCategoriesPage;
import com.arrayprolc.lunadb.util.UtilLoginPage;

public class WebGraphicsHandler {

    public static String handleResponse(String query, String response, String address) {
        query = query.replace("#?createcategory=", "#?categories!");
        if (query.equalsIgnoreCase("#login-page")) {
            return handleLoginPage();
        }
        if (query.startsWith("#?categories")) {
            if (!LoginManager.getInstance().isLoggedIn(address)) {
                return handleLoginPage();
            }
            return handleCategoriesPage(query.split("-"), address);
        }
        response = response.replaceFirst("FAILED: ", "");
        response = response.replaceFirst("SUCCESS: ", "");
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(UtilBootstrap.generateTitle("LunaDB Results"));
        lines.add(Tag.open("body"));
        if (!LoginManager.getInstance().isLoggedIn(address)) {
            lines.addAll(UtilBootstrap.createError("You must sign in to access this page. The login page can be found <a href=\"/\">here</a>.</b></h5>"));
        }
        lines.addAll(UtilBootstrap.container("Results", "Query: " + query, response));
        lines.addAll(UtilBootstrap.containerOpen());
        lines.addAll(UtilBootstrap.generateTabs("Console"));
        lines.addAll(UtilBootstrap.containerClose());

        lines.addAll(UtilBootstrap.actionForm("publicKey"));
        lines.add(Tag.close("body"));
        lines.add(Tag.close("html"));
        String builder = "";
        for (String line : lines) {
            builder = builder + line + "\n";
        }
        return builder;
    }

    public static String handleLoginPage() {
        String builder = "";
        for (String line : UtilLoginPage.getLoginPage()) {
            builder = builder + line + "\n";
        }
        return builder;
    }

    public static String handleCategoriesPage(String[] args, String ip) {
        String builder = "";
        for (String line : UtilCategoriesPage.getCategoriesPage(args, ip)) {
            builder = builder + line + "\n";
        }
        return builder;
    }

}
