package com.arrayprolc.lunadb.commands;

import java.util.ArrayList;
import java.util.Collections;

import com.arrayprolc.lunadb.command.Command;
import com.arrayprolc.lunadb.command.CommandManager;

public class CommandHelp extends Command {

    public CommandHelp() {
        super("help");
        this.setDescription("See a list of commands!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        String string = "---Start of help transcript---\n";
        ArrayList<String> helpKeys = new ArrayList<String>();
        for (Command command : CommandManager.getInstance().commands) {
            helpKeys.add(command.getName() + " : " + command.getDescription() + "\n");
        }
        Collections.sort(helpKeys);
        for (String command : helpKeys) {
            string = string + command;
        }
        string = string + "---End of help transcript---";
        return (string);
    }

}
