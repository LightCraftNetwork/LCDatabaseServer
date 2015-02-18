package com.lightcraftmc.database.util;

import java.util.ArrayList;

import com.lightcraftmc.database.LCDatabaseServer;

public class UtilBootstrap {

    private static String lang = "en";

    public static final String DOCTYPE_HTML = "<!DOCTYPE html>";
    public static final String BOOTSTRAP_URL_CSS = "http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css";
    public static final String JQUERY_URL_JS = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js";
    public static final String BOOTSTRAP_URL_JS = "http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js";

    /**
     * Does NOT generate a body!
     */
    public static ArrayList<String> generateTitle(String pageTitle) {
        pageTitle = pageTitle.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(DOCTYPE_HTML);
        lines.add(Tag.tag("html lang=\"" + lang + "\""));
        lines.add(Tag.open("head"));
        lines.add(Tag.open("title") + pageTitle + Tag.close("title"));
        lines.add(Tag.tag("meta charset=\"utf-8\""));
        lines.add(Tag.tag("meta name=\"viewport\" content=\"width=device-width, initial-scale=1\""));
        lines.add(Tag.tag("link rel=\"stylesheet\" href=\"" + BOOTSTRAP_URL_CSS + "\""));
        lines.add(Tag.tag("script src=\"" + JQUERY_URL_JS + "\"") + Tag.close("script"));
        lines.add(Tag.tag("script src=\"" + BOOTSTRAP_URL_JS + "\"") + Tag.close("script"));
        lines.add(Tag.close("head"));
        return lines;
    }

    /**
     * Requires a body, and creates a container.
     */
    public static ArrayList<String> container(String title, String subtitle, String text) {
        title = title.replace("\n", "<br>");
        subtitle = subtitle.replace("\n", "<br>");
        text = text.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"container\""));
        lines.add(Tag.open("h1") + title + Tag.close("h1"));
        lines.add(Tag.open("p") + subtitle + Tag.close("p"));
        lines.add(Tag.open("pre"));
        lines.add(text);
        lines.add(Tag.close("pre"));
        lines.add(Tag.close("div"));
        return lines;
    }

    public static ArrayList<String> actionForm(String accessToken) {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            lines.add(Tag.tag("div class=\"container\""));
            lines.add(Tag.open("form action=\"/formatted:[accessToken]!!\" class=\"well span9\" method=\"GET\"").replace("[accessToken]", accessToken));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lines.add(Tag.open("label") + "Query" + Tag.close("label"));
        lines.add(Tag.tag("input type=\"text\" name=\"query\" class=\"span9\" placeholder=\"Server Query\""));
        lines.add(Tag.close("form"));
        lines.add(Tag.close("div"));
        return lines;
    }
}
