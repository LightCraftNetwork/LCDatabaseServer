package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;

public class CommandRetrieveFile extends Command {

    public CommandRetrieveFile() {
        super("retrievefile");
        this.setDescription("Retrieve a set file from the database!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        return "SUCCESS: IN PROGRESS. Not finished yet.";
    }

}
