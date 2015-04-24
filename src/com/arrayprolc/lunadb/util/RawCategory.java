package com.arrayprolc.lunadb.util;

import java.io.File;
import java.util.ArrayList;

public class RawCategory {

    private String name;
    private ArrayList<File> items = new ArrayList<File>();
    private File file;

    public RawCategory(String name, File file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<File> getItems() {
        return items;
    }

    public void setItems(ArrayList<File> items) {
        this.items = items;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
