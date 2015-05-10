package com.arrayprolc.lunadb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.arrayprolc.lunadb.LunaDB;

public class UtilFile {

    public static String TEXT_EXTENSION = ".luna-text";
    public static String SCRIPT_EXTENSION = ".luna-scr";

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
            for(String s : value.split("\n")){
                out.println(s);
            }
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
        String path = "data\\" + category.toLowerCase() + "/" + key + extension;

        File file = new File(path);
        String fileName = file.getAbsolutePath();
        String line = "";
        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<String> lines = new ArrayList<String>();

            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }

            boolean first = true;
            String builder = "";
            for (String s : lines) {
                if (first) {
                    builder = s;
                    first = false;
                } else {
                    builder += "\n" + s;
                }
            }

            bufferedReader.close();
            return builder;

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
        String path = "data\\" + category.toLowerCase() + "\\" + key + extension;
        File file = new File(path);
        file.delete();
        return;
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName
     *            ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    public static String exportResource(String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = LunaDB.class.getResourceAsStream(resourceName); // note
                                                                     // that
                                                                     // each /
                                                                     // is a
                                                                     // directory
                                                                     // down in
                                                                     // the
                                                                     // "jar tree"
                                                                     // been the
                                                                     // jar the
                                                                     // root of
                                                                     // the tree
            if (stream == null) {
                throw new Exception("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(LunaDB.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
            resStreamOut = new FileOutputStream(jarFolder + resourceName);
            while ((readBytes = stream.read(buffer)) > 0) {
                resStreamOut.write(buffer, 0, readBytes);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            stream.close();
            resStreamOut.close();
        }

        return jarFolder + resourceName;
    }

}
