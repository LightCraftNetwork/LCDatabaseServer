package com.arrayprolc.lunadb.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

/**
 * 
 * @author Justin
 * @info This class is to be implemented in your own programs. You can also grab
 *       the lunadb complete binary or the API binary on GitHub. Enjoy! <3
 *       http://www.github.com/lunadb
 *
 */
public class LunaDBScript {

    private String path;

    private String category;

    private String runName;

    public String getRunName() {
        return runName;
    }

    public void setRunName(String runName) {
        this.runName = runName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path, String runName) {
        this.path = path;
        this.runName = runName;
    }

    public LunaDBScript(String path, String category, String runName) {
        super();
        this.path = path;
        this.category = category;
        this.runName = runName;
    }

    public boolean send(LunaServer server, Class mainClass) throws Exception {
        String builder = "";
        File f = getFile(mainClass);
        Scanner sc;
        try {
            sc = new Scanner(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            throw e;
        }
        while (sc.hasNext()) {
            builder += sc.nextLine() + "\n";
        }
        sc.close();
        builder = Base64.encodeBase64String(builder.getBytes());
        String query = "insertscript " + category + " " + getRunName() + " " + builder;
        String result;
        try {
            result = server.query(query);
        } catch (Exception e) {
            throw e;
        }
        deleteTemp();
        return result.startsWith("SUCCESS");
    }

    public ArrayList<String> run(LunaServer server) {
        ArrayList<String> results = new ArrayList<String>();
        String query = "exec " + category + " " + runName;
        String result;
        try {
            result = server.query(query);
        } catch (Exception e) {
            results.add("<FAILED> " + Arrays.toString(e.getStackTrace()));
            return results;
        }
        if (!result.startsWith("RESULTS: ")) {
            results.add("<FAILED-FROMSERVER> " + result);
            return results;
        }
        int s = 10;
        result = result.substring(s);
        result = result.substring(0, result.length() - 1);
        for (String ss : result.split(", ")) {
            results.add(ss);
        }
        return results;
    }

    private static String removeLastChar(String str) {
        return str.substring(0, str.length() - 1);
    }

    private File getFile(Class mainClass) {
        File temp = null;
        if (path.startsWith("*")) {
            try {
                temp = exportTemp(mainClass);
            } catch (Exception e) {
                temp = null;
            }
        } else {
            temp = new File(path);
        }
        return temp;
    }

    private File exportTemp(Class mainClass) throws Exception {
        String tempPath = path.substring(1);
        new File("./temp/").mkdirs();
        File f = new File(exportResource(mainClass, tempPath));
        File f2 = new File("./temp/" + f.getName());
        Files.move(f.toPath(), f2.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return f2;
    }

    private void deleteTemp() {
        File f = new File("./temp/" + path);
        f.delete();
    }

    /**
     * Export a resource embedded into a Jar file to the local file path.
     *
     * @param resourceName
     *            ie.: "/SmartLibrary.dll"
     * @return The path to the exported resource
     * @throws Exception
     */
    private static String exportResource(Class mainClass, String resourceName) throws Exception {
        InputStream stream = null;
        OutputStream resStreamOut = null;
        String jarFolder;
        try {
            stream = mainClass.getResourceAsStream(resourceName);
            if (stream == null) {
                throw new IOException("Cannot get resource \"" + resourceName + "\" from Jar file.");
            }

            int readBytes;
            byte[] buffer = new byte[4096];
            jarFolder = new File(mainClass.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath().replace('\\', '/');
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
