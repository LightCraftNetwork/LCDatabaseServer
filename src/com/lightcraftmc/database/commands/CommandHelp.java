package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.command.CommandManager;

public class CommandHelp extends Command {

	public CommandHelp() {
		super("help");
	}

	@Override
	public String runCommand(boolean isLocal, String[] args) {
		for(Command command : CommandManager.getInstance().commands){
			System.out.println("[HELP] " + command.getName());
		}
		return("Command will be updated soon!");
	}

}
