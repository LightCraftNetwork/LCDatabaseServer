package com.arrayprolc.lunadb.util;

import java.io.File;
import java.util.ArrayList;

public class UtilGenerateCategories {

    public static ArrayList<RawCategory> getCategories() {
        ArrayList<File> files = new ArrayList<File>();
        for (File file : RecurUtil.listf("data\\")) {
            if (file.isDirectory()) {
                files.add(file);
            }
        }
        ArrayList<RawCategory> categories = UtilGenerateCategories.getCategories(files);
        return categories;
    }
    
    public static ArrayList<RawCategory> getCategories(ArrayList<File> categories) {
        ArrayList<RawCategory> cats = new ArrayList<RawCategory>();
        for (File f : categories) {
            RawCategory r = new RawCategory(f.getPath().split("data")[1].substring(1), f);
            for (File f2 : f.listFiles()) {
                r.getItems().add(f2);
            }
            cats.add(r);
        }
        return cats;

    }
}
