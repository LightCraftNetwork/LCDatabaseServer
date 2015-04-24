package com.arrayprolc.lunadb.commands;

import java.util.ArrayList;
import java.util.Arrays;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.command.CommandManager;

public class CommandExecAs extends Command {

    public CommandExecAs() {
        super("execas");
        this.setDescription("Run as a specified script!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if(args.length < 2){
            return "Usage: execas runner command";
        }
        String runner = args[0];
        ArrayList<String> collectionOfStrings = new ArrayList<String>();
        int i = 0;
        for (String s : args) {
            if(i != 0){
                collectionOfStrings.add(s);
            }
            i++;
        }
        System.out.println(Arrays.toString(collectionOfStrings.toArray()));
        return interpret(runner, create(collectionOfStrings));
    }
    
    private String create(ArrayList<String> collectionOfStrings){
        StringBuilder result = new StringBuilder();
        for(String string : collectionOfStrings) {
            result.append(string);
            result.append(" ");
        }
        return result.length() > 0 ? result.substring(0, result.length() - 1): "";
    }
    
    public String interpret(String runner, String s) {
        System.out.println("[Luna] |" + s + "|");
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
                    return command.runCommandFromScript(runner, args);
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
                        return command.runCommandFromScript(runner, args);
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
