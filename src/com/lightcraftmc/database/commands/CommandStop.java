package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.LCDatabaseServer;
import com.lightcraftmc.database.command.Command;

public class CommandStop extends Command {

	public CommandStop() {
		super("stop");
		this.setDescription("Stop the server!");
	}

	@Override
	public String runCommand(boolean isLocal, String[] args) {
		if (!isLocal) {
			if (args.length != 2) {
				return "You must specify an access token if the query is not local!";
			}
			if (!args[1].equals(LCDatabaseServer.getManager().getAccessKey())) {
				return "That is an incorrect access token!";
			}
		} else {
			if (args.length == 0) {
				return "You must specify a reason!";
			}
		}
		LCDatabaseServer.getManager().shutdownServer();
		System.exit(10);
		return "SYSTEM SHUTTING DOWN: " + "isLocal: " + isLocal + " " + " Reason: " + args[0];


	}

}
