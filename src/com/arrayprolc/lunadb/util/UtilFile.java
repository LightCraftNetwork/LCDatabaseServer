package com.arrayprolc.lunadb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class UtilFile {

    public static void save(String category, String key, String value, String extension) {
        String path = "data\\" + category.toLowerCase() + "\\" + key + extension;
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
        save(category, key, ".lcdb-text");
    }
    
    public static void saveScript(String category, String key, String value) {
        save("scripts\\" + category, key, ".lcdb-scr");
    }
    
    public static String load(String category, String key) {
        return load (category, key, ".lcdb-text");
    }
    
    public static String loadScript(String category, String key) {
        return load("scripts\\" + category, key, ".lcdb-scr");
    }

    public static String load(String category, String key, String extension) {
        String path = "data\\" + category.toLowerCase() + "\\" + key + extension;
        File file = new File(path);
        String fileName = file.getAbsolutePath();
        String line = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                bufferedReader.close();
                return line;
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
          //  System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
         //   System.out.println("Error reading file '" + fileName + "'");
            // Or we could just do this:
            // ex.printStackTrace();
        }
        return line;
    }
    
    public static void delete(String category, String key) {
        String path = "data\\" + category.toLowerCase() + "\\" + key + ".lcdb-text";
        File file = new File(path);
        file.delete();
        return;
    }

}
