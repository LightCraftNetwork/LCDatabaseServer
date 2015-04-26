package com.arrayprolc.lunadb.commands;

import java.util.ArrayList;
import java.util.Arrays;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.command.CommandManager;
import com.arrayprolc.lunadb.util.UtilFile;

public class CommandExec extends Command {

    public CommandExec() {
        super("exec");
        this.setDescription("Run a script!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length < 2) {
            return "Usage: exec category scriptname";
        }
        String scriptName = args[1];
        scriptName = scriptName.replace(".luna-scr", "");
        String s = UtilFile.loadScript(args[0], scriptName);
        ArrayList<String> results = new ArrayList<String>();
        for (String ss : s.split("\n")) {
            if (!ss.equals("") && !ss.equals(" ") && !ss.startsWith("//")) {
                results.add(interpret("execas " + scriptName + " " + ss));
            } else {
                results.add("<blank>");
            }
        }
        return "RESULTS: " + Arrays.toString(results.toArray());
    }

    public String interpret(String s) {
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
                    return (command.runCommand("127.0.0.1", true, args, true));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        for (Command command : CommandManager.getInstance().commands) {
            for (String subName : command.getSubNames()) {
                try {
                    if (subName.equalsIgnoreCase(commandName)) {
                        return (command.runCommand("127.0.0.1", true, args, true));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
        return ("[Luna] [ERROR] Unknown command. Use \"help\" command to get a list of commands.");
    }

    public String getArgs(String s) {
        String remove = s.split(" ")[0];
        s = s.replaceFirst(remove + " ", "");
        return s;
    }
}
