package com.arrayprolc.lunadb.util;

import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author "stacker" and "Kishan" from StackOverflow with modifications by
 *         Justin Source:
 *         http://stackoverflow.com/questions/2056221/recursively-list-files
 *         -in-java
 *
 */
public class FileWalker {
    public ArrayList<File> walk(String path) {
        ArrayList<File> files = new ArrayList<File>();

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
            return null;

        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath());
            } else {
                files.add(f);
            }
        }
        return files;
    }

    public void renameFile(String path) {

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null)
            return;

        for (File f : list) {
            if (f.isDirectory()) {
                renameFile(f.getAbsolutePath());
            } else {
                if(getFileExtension(f.getAbsolutePath()).contains("lcdb-text")){
                    renameFileExtension(f.getAbsolutePath(), "luna-text");
                }
                if(getFileExtension(f.getAbsolutePath()).contains("lcdb-scr")){
                    renameFileExtension(f.getAbsolutePath(), "luna-scr");
                }
            }
        }
    }

    private static boolean renameFileExtension(String source, String newExtension) {
        String target;
        String currentExtension = getFileExtension(source);

        if (currentExtension.equals("")) {
            target = source + "." + newExtension;
        } else {
            target = source.replaceFirst(Pattern.quote("." + currentExtension) + "$", Matcher.quoteReplacement("." + newExtension));

        }
        return new File(source).renameTo(new File(target));
    }

    private static String getFileExtension(String f) {
        String ext = "";
        int i = f.lastIndexOf('.');
        if (i > 0 && i < f.length() - 1) {
            ext = f.substring(i + 1);
        }
        return ext;
    }
}