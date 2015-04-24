package com.lightcraftmc.database.command;

import java.util.ArrayList;

import com.lightcraftmc.database.commands.CommandDelete;
import com.lightcraftmc.database.commands.CommandExecAs;
import com.lightcraftmc.database.commands.CommandHelp;
import com.lightcraftmc.database.commands.CommandInsert;
import com.lightcraftmc.database.commands.CommandList;
import com.lightcraftmc.database.commands.CommandLogin;
import com.lightcraftmc.database.commands.CommandReload;
import com.lightcraftmc.database.commands.CommandRetrieve;
import com.lightcraftmc.database.commands.CommandStop;

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
