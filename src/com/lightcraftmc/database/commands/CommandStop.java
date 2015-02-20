package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.LCDatabaseServer;
import com.lightcraftmc.database.command.Command;

public class CommandStop extends Command {

    public CommandStop() {
        super("stop");
        this.setDescription("Stop the server!");
    }

    @Override
    public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
        if (args.length == 0) {
            return "You must specify a reason!";
        }
        LCDatabaseServer.getManager().shutdownServer();
        System.exit(0);
        return "SYSTEM SHUTTING DOWN: " + "isLocal: " + isLocal + " " + " Reason: " + args[0];

    }

}
