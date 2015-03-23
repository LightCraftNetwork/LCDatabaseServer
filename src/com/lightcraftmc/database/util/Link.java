package com.lightcraftmc.database.util;

public class Link {

    private String link, title;
    private boolean isSeperator = false;

    public Link(String link, String title) {
        super();
        this.link = link;
        this.title = title;
        link = link.replace("#", "[poundsign]");
    }
    
    public static Link seperator(){
        Link l = new Link("", "");
        l.isSeperator = true;
        return l;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        link = link.replace("#", "[poundsign]");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public boolean isSeperator(){
        return isSeperator;
    }

}
