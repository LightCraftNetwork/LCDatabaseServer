package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;

public class CommandLogin extends Command {

    public CommandLogin() {
        super("login");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args) {
        
        return ip;
    }

}
