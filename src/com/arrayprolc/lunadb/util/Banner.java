package com.arrayprolc.lunadb.util;

import java.util.ArrayList;

public class Banner {

    private String text, title;
    private String glyphIcon = "exclamation-sign";
    private ButtonType type;
    private boolean shouldShow;

    public Banner(String text, String title, String glyphIcon, ButtonType type, boolean shouldShow) {
        super();
        this.text = text;
        this.title = title;
        this.glyphIcon = glyphIcon;
        this.type = type;
        this.shouldShow = shouldShow;
    }

    public Banner(String text, String title, ButtonType type, boolean shouldShow) {
        super();
        this.text = text;
        this.title = title;
        this.type = type;
        this.shouldShow = shouldShow;
    }
    
    public static Banner blank(){
        return new Banner("", "", ButtonType.DEFAULT, false);
    }
    
    public static Banner defaultSuccess(){
        return defaultSuccess("The action completed successfully.");
    }
    
    public static Banner defaultSuccess(String message){
        return new Banner(message, "Success:", "ok", ButtonType.SUCCESS, true);
    }
    
    public static Banner defaultFailed(){
        return defaultFailed("The action failed.");
    }
    
    public static Banner defaultFailed(String message){
        return new Banner(message, "Error:", ButtonType.DANGER, true);
    }

    public String getGlyphIcon() {
        return glyphIcon;
    }

    public void setGlyphIcon(String glyphIcon) {
        this.glyphIcon = glyphIcon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ButtonType getType() {
        return type;
    }

    public void setType(ButtonType type) {
        this.type = type;
    }

    public boolean isShouldShow() {
        return shouldShow;
    }

    public void setShouldShow(boolean shouldShow) {
        this.shouldShow = shouldShow;
    }

    public ArrayList<String> getHTML() {
        if (!shouldShow) {
            return new ArrayList<String>();
        }
        text = text.replace("\n", "<br>");
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Tag.tag("div class=\"alert alert-" + type.toString().toLowerCase() + "\" role=\"alert\""));
        lines.add(Tag.tag("span class=\"glyphicon glyphicon-" + glyphIcon + "\" aria-hidden=\"true\"") + Tag.close("span"));
        lines.add(Tag.tag("span class=\"sr-only\"") + getTitle() + Tag.close("span"));
        lines.add(text);
        lines.add(Tag.close("div"));
        return lines;
    }

}
