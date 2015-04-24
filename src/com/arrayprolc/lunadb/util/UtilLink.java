package com.arrayprolc.lunadb.util;

import java.util.ArrayList;

public class UtilLink {

    public static ArrayList<Link> getPages(){
        ArrayList<Link> links = new ArrayList<Link>();
        links.add(new Link("/formatted:publicKey!!?query=list", "Console"));
        links.add(new Link("/?categories", "Categories"));
        return links;
    }
    
}
