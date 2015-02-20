package com.lightcraftmc.database.util;

import java.io.File;
import java.util.ArrayList;

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

    public static ArrayList<String> generateBoxes(boolean dark, String title, String text) {
        title = title.replace("\n", "<br>");
        text = text.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"panel panel-primary\""));
        lines.add(Tag.tag("div class=\"panel-body\""));
        lines.add(title);
        lines.add(Tag.close("div"));
        lines.add(Tag.tag("div class=\"panel-footer\"") + text + Tag.close("div"));
        lines.add(Tag.close("div"));
        return lines;
    }
    
    public static ArrayList<String> generateTable(RawCategory c){
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"panel panel-default\""));
        lines.add("<div class=\"panel-heading\">" + c.getName() + "</div>");
       // lines.add("<div class=\"panel-body\">");
       // lines.add("<p>This category has " + c.getItems().size() + " items.</p>");
       // lines.add(Tag.close("div"));
        lines.add(Tag.tag("table class=\"table\""));
        lines.add("<tr><th>Key</th></tr></thead>");
        lines.add("<tbody>");
        for(File f : c.getItems()){
            lines.add(Tag.open("tr"));
          //  lines.add("<th scope=\"row\">1</th>");
            lines.add(Tag.open("td") + f.getName() + Tag.close("td"));
            lines.add(Tag.close("tr"));
        }
        lines.add(Tag.close("tbody"));
        lines.add(Tag.close("table"));
        lines.add(Tag.close("div"));
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
    
    public static ArrayList<String> containerOpen() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"container\""));
        lines.add(Tag.open("pre"));
        return lines;
    }
    
    public static ArrayList<String> containerClose() {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.close("pre"));
        lines.add(Tag.close("div"));
        return lines;
    }

    /**
     * Requires a body, and creates a container.
     */
    public static ArrayList<String> container(String text) {
        text = text.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"container\""));
        lines.add(Tag.open("pre"));
        lines.add(text);
        lines.add(Tag.close("pre"));
        lines.add(Tag.close("div"));
        return lines;
    }

    /**
     * Requires a body, and creates a container.
     */
    public static ArrayList<String> createError(String text) {
        text = text.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"alert alert-danger\" role=\"alert\""));
        lines.add(Tag.tag("span class=\"glyphicon glyphicon-exclamation-sign\" aria-hidden=\"true\"") + Tag.close("span"));
        lines.add(Tag.tag("span class=\"sr-only\"") + "Error:" + Tag.close("span"));
        lines.add(text);
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
        lines.addAll(createSmallButton("Sign out", "/formatted:[accessToken]!!login%20signout".replace("[accessToken]", accessToken), "warning"));
        lines.addAll(createSmallButton("Stop Server", "/formatted:[accessToken]!!stop%20Button-click_by_user".replace("[accessToken]", accessToken), "danger"));
        lines.addAll(createSmallButton("List Commands", "/formatted:[accessToken]!!help".replace("[accessToken]", accessToken), "info"));
        lines.addAll(createSmallButton("Reload Server", "/formatted:[accessToken]!!reload".replace("[accessToken]", accessToken), "danger"));
        lines.add(Tag.close("div"));
        return lines;
    }

    public static ArrayList<String> loginForm() {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            lines.add(Tag.tag("div class=\"container\""));
            lines.add(Tag.open("form action=\"/formatted:publicKey!!login%20\" class=\"well span9\" method=\"GET\""));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        lines.add(Tag.open("label") + "Access Token" + Tag.close("label"));
        lines.add(Tag.tag("input type=\"text\" name=\"query\" class=\"span9\" placeholder=\"Access Token\""));
        lines.addAll(createSmallButton("?", "/formatted:publicKey!!login%20help", "info"));
        lines.add(Tag.close("form"));

        lines.add(Tag.close("div"));
        return lines;
    }

    public static ArrayList<String> createSignOutButton(String link) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("a href=\"" + link + "\" class=\"btn btn-success btn-lg\""));
        lines.add("<span class=\"glyphicon glyphicon-remove\"></span> " + Tag.open("p") + "Logout" + Tag.close("p"));
        lines.add(Tag.close("a"));
        return lines;
    }

    public static ArrayList<String> createSmallButton(String title, String link, String btnType) {
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("a href=\"" + link + "\" class=\"btn btn-success btn-" + btnType + "\""));
        lines.add(title);
        lines.add(Tag.close("a"));
        return lines;
    }
}
