package com.arrayprolc.lunadb;

import java.io.File;
import java.nio.file.Files;

import com.arrayprolc.lunadb.util.UtilFile;

public class SetupQuestions {

    public static String send(String query) {
        try {
            if (query.startsWith("/finished-setup?")) {
                UtilFile.save("@server", "setup", "complete:true");
                UtilFile.save("@server", "location", query.split("/finished-setup?")[1]);
                Files.deleteIfExists(new File("./location-setup.html").toPath());
                Files.deleteIfExists(new File("./working-setup.html").toPath());
                return "/";
            }
            if (query.startsWith("/location-setup?")) {
                UtilFile.save("@server", "setup", "complete:true");
                UtilFile.save("@server", "type", query.split("/location-setup?")[1]);
                Files.deleteIfExists(new File("./location-setup.html").toPath());
                Files.deleteIfExists(new File("./working-setup.html").toPath());
                return "/";
            }
            if (query.startsWith("/working-setup?")) {
                UtilFile.save("@server", "usertype", query.split("/working-setup?")[1]);
                return UtilFile.exportResource("/location-setup.html");
            }
            return UtilFile.exportResource("/working-setup.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/";
    }

    public static boolean isDoneWithQuestions() {
        String s = UtilFile.load("@server", "setup");
        return s.startsWith("complete:");
    }

}
