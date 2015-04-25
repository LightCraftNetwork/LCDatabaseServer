package com.arrayprolc.lunadb.command;

import java.util.ArrayList;

import com.arrayprolc.lunadb.commands.CommandDelete;
import com.arrayprolc.lunadb.commands.CommandExecAs;
import com.arrayprolc.lunadb.commands.CommandHelp;
import com.arrayprolc.lunadb.commands.CommandInsert;
import com.arrayprolc.lunadb.commands.CommandList;
import com.arrayprolc.lunadb.commands.CommandLogin;
import com.arrayprolc.lunadb.commands.CommandReload;
import com.arrayprolc.lunadb.commands.CommandRetrieve;
import com.arrayprolc.lunadb.commands.CommandStop;

public class CommandManager {

    private static CommandManager manager;
    public ArrayList<Command> commands = new ArrayList<Command>();

    public static CommandManager getInstance() {
        if (manager == null) {
            manager = new CommandManager();
        }
        return manager;
    }

    public static void initCommands() {
        getInstance().commands.clear();
        getInstance().commands.add(new CommandStop());
        getInstance().commands.add(new CommandHelp());
        getInstance().commands.add(new CommandInsert());
        getInstance().commands.add(new CommandRetrieve());
        getInstance().commands.add(new CommandList());
        getInstance().commands.add(new CommandReload());
        getInstance().commands.add(new CommandLogin());
        getInstance().commands.add(new CommandDelete());
        getInstance().commands.add(new CommandExecAs());
    }

}
