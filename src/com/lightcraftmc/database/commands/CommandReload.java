package com.lightcraftmc.database.commands;

import com.lightcraftmc.database.command.Command;
import com.lightcraftmc.database.command.CommandManager;

public class CommandReload extends Command {

	public CommandReload() {
		super("reload");
		this.setDescription("Reload the server! (Not recommended)");
	}

	@Override
	public String runCommand(String ip, boolean isLocal, String[] args, boolean isAdmin) {
		long time = System.currentTimeMillis();
		CommandManager.getInstance().commands.clear();
		CommandManager.initCommands();
		return "SUCCESS: Reload complete in " + (System.currentTimeMillis() - time) + "ms";

	}

}
