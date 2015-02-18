package com.lightcraftmc.database.util;

public class Tag {

    public static String open(String tag) {
        return "<" + tag + ">";
    }

    public static String close(String tag) {
        return "</" + tag + ">";
    }

    public static String tag(String tag) {
        return "<" + tag + ">";
    }
}
