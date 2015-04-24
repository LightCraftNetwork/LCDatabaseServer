package com.arrayprolc.lunadb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.arrayprolc.lunadb.util.FileWalker;
import com.arrayprolc.lunadb.util.UtilFile;

public class LegacyConverter {

    public static void run() {
        String s = UtilFile.load("@server", "version");
        System.out.println("[Luna] Converting database to version " + LunaDB.fileVer + ". (and making a backup)");
        if (!s.equals(LunaDB.fileVer + "")) {
            try {
                copyDirectory(new File("./data/"), new File("./data-backup/"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            convert();
            System.out.println("[Luna] Done converting!");
            UtilFile.save("@server", "version", LunaDB.fileVer + "");
        }
    }

    private static void convert() {
        System.out.println("[Luna] Beginning convert...");
        FileWalker walker = new FileWalker();
        walker.renameFile("./data/");
    }

    public static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

    private static void copyFile(File source, File target) throws IOException {
        try (InputStream in = new FileInputStream(source); OutputStream out = new FileOutputStream(target)) {
            byte[] buf = new byte[1024];
            int length;
            while ((length = in.read(buf)) > 0) {
                out.write(buf, 0, length);
            }
        }
    }

}
