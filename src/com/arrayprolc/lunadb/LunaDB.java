package com.arrayprolc.lunadb;

import com.arrayprolc.lunadb.command.CommandManager;

public class LunaDB {

    private static int port = 0;
    private static String accessKey = "";
    private static Manager manager;
    public final CommandInterpreter interpreter = new CommandInterpreter();

    public static double fileVer = 2.03d;

    public static void main(String[] args) {
        LunaStarter.main(args);
    }

    public void run(String[] args) {
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
        manager.startServer();
        if (args.length != 3) {
            CommandManager.initCommands();
            interpreter.listen();
        }

    }

    public static Manager getManager() {
        return manager;
    }

}
