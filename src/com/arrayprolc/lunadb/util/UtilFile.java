package com.arrayprolc.lunadb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.arrayprolc.lunadb.LunaDB;

public class UtilFile {

    public static String TEXT_EXTENSION = ".luna-text";
    public static String SCRIPT_EXTENSION = ".luna-scr";

    public static void save(String category, String key, String value, String extension) {
        String path = LunaDB.getManager().getDataFile() + category.toLowerCase() + "\\" + key + extension;
        File file = new File(path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter out = new PrintWriter(file.getAbsolutePath());
            out.print(value.replace("\n", "\\n"));
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void save(String category, String key, String value) {
        save(category, key, value, TEXT_EXTENSION);
    }

    public static void saveScript(String category, String key, String value) {
        save("scripts\\" + category, key, value, SCRIPT_EXTENSION);
    }

    public static String load(String category, String key) {
        return load(category, key, TEXT_EXTENSION);
    }

    public static String loadScript(String category, String key) {
        return load("scripts\\" + category, key, SCRIPT_EXTENSION);
    }

    public static String load(String category, String key, String extension) {
        String path = LunaDB.getManager().getDataFile() + category.toLowerCase() + "\\" + key + extension;
        File file = new File(path);
        String fileName = file.getAbsolutePath();
        String line = "";
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                bufferedReader.close();
                return line;
            }

            bufferedReader.close();
        } catch (Exception ex) {

        }
        return line;
    }

    public static void delete(String category, String key) {
        delete(category, key, TEXT_EXTENSION);
    }

    public static void deleteScript(String category, String key) {
        delete(category, key, SCRIPT_EXTENSION);
    }

    public static void delete(String category, String key, String extension) {
        String path = LunaDB.getManager().getDataFile() + category.toLowerCase() + "\\" + key + extension;
        File file = new File(path);
        file.delete();
        return;
    }

}
