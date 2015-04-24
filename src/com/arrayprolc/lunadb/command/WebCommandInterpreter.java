package com.arrayprolc.lunadb.command;

public class WebCommandInterpreter {

    private static WebCommandInterpreter instance;

    public static WebCommandInterpreter getInstance() {
        if (instance == null) {
            instance = new WebCommandInterpreter();
        }
        return instance;
    }

    public String interpret(String s, String ip, boolean isAdmin) {
        String commandName;
        String[] args;
        if (s.contains(" ")) {
            commandName = s.split(" ")[0];
            args = getArgs(s).split(" ");
        } else {
            commandName = s;
            args = new String[] {};
        }
        for (Command command : CommandManager.getInstance().commands) {
            if (command.getName().equalsIgnoreCase(commandName)) {
                try {
                    return command.prepareCommandForRun(ip, false, args, isAdmin);
                } catch (Exception ex) {
                    // TODO log
                    ex.printStackTrace();
                    return "FAILED: An internal error has occured while trying to parse that command.";

                }
            }
        }
        for (Command command : CommandManager.getInstance().commands) {
            for (String subName : command.getSubNames()) {
                try {
                    if (subName.equalsIgnoreCase(commandName)) {
                        return command.prepareCommandForRun(ip, false, args, isAdmin);
                    }
                } catch (Exception ex) {
                    // TODO log
                    ex.printStackTrace();
                    return "FAILED: An internal error has occured while trying to parse that command.";

                }

            }
        }
        return "FAILED: Unknown command. Use \"help\" command to get a list of commands.";
    }

    public String getArgs(String s) {
        String remove = s.split(" ")[0];
        s = s.replaceFirst(remove + " ", "");
        return s;
    }

}
