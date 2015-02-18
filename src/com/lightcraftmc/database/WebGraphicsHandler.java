package com.lightcraftmc.database;

import java.util.ArrayList;

import com.lightcraftmc.database.util.Tag;
import com.lightcraftmc.database.util.UtilBootstrap;

public class WebGraphicsHandler {

    public static String handleResponse(String query, String response) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.addAll(UtilBootstrap.generateTitle("LCDatabaseServer Results"));
        lines.add(Tag.open("body"));
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

}
