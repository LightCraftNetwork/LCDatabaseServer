package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.command.CommandManager;

public class CommandReload extends Command {

	public CommandReload() {
		super("reload");
		this.setDescription("Reload the server! (Not recommended)");
	}

	@Override
	public String runCommand(boolean isLocal, String[] args) {
		long time = System.currentTimeMillis();
		if (args.length == 0) {
			return "Usage: list filesystem (use '@a' for root data dir)";
		}
		CommandManager.initCommands();
		return "SUCCESS: Reload complete.";

	}

}
