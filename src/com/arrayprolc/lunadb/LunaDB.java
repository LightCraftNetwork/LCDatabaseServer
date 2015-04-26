package com.arrayprolc.lunadb;

import java.io.File;
import java.util.Scanner;

import com.arrayprolc.lunadb.command.CommandManager;

/**
 * 
 * @author Justin & Jacob
 *
 */
public class LunaDB {

    private static int port = 0;
    private static String accessKey = "";
    private static Manager manager;
    public static final CommandInterpreter interpreter = new CommandInterpreter();
    public static final Scanner _SCANNER = new Scanner(System.in);
    private static LunaDB database;

    public static double fileVer = 2.04d;

    public static void main(String[] args) {
        database = new LunaDB();
        database.run(args);
    }

    public void run(String[] args) {
        new File("./data/").mkdirs();
        LegacyConverter.run();
        if (args.length < 2) {
            System.out.println("[Luna] REQUIRED ARGUMENTS: int:port accessKey");
            System.exit(0);
            return;
        }
        System.out.println("[Luna] Beginning server start...");
        try {
            System.out.println("[Luna] Checking port...");
            port = Integer.parseInt(args[0]);
        } catch (Exception ex) {
            System.out.println(args[0] + " is not an integer. Please retry port with an integer.");
            System.exit(0);
            return;
        }
        System.out.println("[Luna] Port is OK.");
        System.out.println("[Luna] Checking access key.");
        String blockedChars = "?,!,=";
        for (String b : blockedChars.split(",")) {
            if (args[1].contains(b)) {
                System.out.println("[Luna] Access Key cannot include the following: " + blockedChars.replace("[comma]", ","));
                System.exit(0);
                return;
            }
        }
        accessKey = args[1];
        System.out.println("[Luna] Access key is OK.");
        System.out.println("[Luna] Setting up manager...");
        manager = new Manager();
        manager.setPort(port);
        manager.setAccessKey(accessKey);
        System.out.println("[Luna] Manager has been created.");
        System.out.println("[Luna] All arguments are parsed correctly. Starting server on port " + manager.getPort());
        if (!SetupQuestions.isDoneWithQuestions()) {
            System.out.println("[Luna] It is imperative that you visit the web interface to set up the database settings!");
        }
        manager.startServer();
        if (args.length != 3) {
            CommandManager.initCommands();
            interpreter.interpretString("exec @server launch");
            interpreter.listen();
        }

    }

    public static Manager getManager() {
        return manager;
    }

}
