package com.arrayprolc.lunadb;

import java.util.Scanner;

import com.arrayprolc.lunadb.util.FileWalker;
import com.arrayprolc.lunadb.util.UtilFile;

public class LegacyConverter {

    public static void run() {
        String s = UtilFile.load("@server", "version");
        System.out.println("Converting database to version " + LunaDB.fileVer + ". (Make backups!) Press Return to continue.");
        if (!s.equals(LunaDB.fileVer + "")) {
            convert();
            System.out.println("Done converting!");
            UtilFile.save("@server", "version", LunaDB.fileVer + "");
        }
    }

    private static void convert() {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();
        System.out.println("Beginning convert...");
        FileWalker walker = new FileWalker();
        walker.renameFile("./data/");
    }

}
